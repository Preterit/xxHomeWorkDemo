package com.xiangxue.rx.rx_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiangxue.xxhomeworkdemo.R;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = "FilterActivity";

    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);


        RxTextView.textChanges(editText)
                .debounce(1, TimeUnit.SECONDS)
                //跳过第一次请求,因为初始输入框的空字符串
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        charSequence -> tvText.setText(charSequence.toString()),
                        Throwable::printStackTrace,
                        () -> {
                            Toast.makeText(FilterActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"onComplete()");
                        }
                );
    }
}
