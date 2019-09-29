package com.xiangxue.rx.rx_activity;

import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiangxue.xxhomeworkdemo.R;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GetCodeActivity extends AppCompatActivity {

    @BindView(R.id.btn_code)
    Button btnCode;

    private int SECOND = 20;
    private Observable<Boolean> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_code);
        ButterKnife.bind(this);

        observable = RxView.clicks(btnCode)
                .throttleFirst(SECOND, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(o -> false)
                .doOnNext(btnCode::setEnabled);

        observable.subscribe(
                s -> {
                    Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                            .take(SECOND)
                            .subscribe(
                                    aLong -> {
                                        RxTextView.text(btnCode).accept("剩余" + (SECOND - aLong) + "秒");
                                    },
                                    Throwable::printStackTrace,
                                    () -> {
                                        RxTextView.text(btnCode).accept("获取验证码");
                                        RxView.enabled(btnCode).accept(true);
                                    }
                            );
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (observable != null) {
            observable.unsubscribeOn(AndroidSchedulers.mainThread());  // 放置泄露
            observable = null;
        }
    }
}
