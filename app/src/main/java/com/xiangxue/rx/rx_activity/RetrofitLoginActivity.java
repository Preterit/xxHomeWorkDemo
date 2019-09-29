package com.xiangxue.rx.rx_activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiangxue.xxhomeworkdemo.R;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * rx 操作符
 *  多个状态监听,结合操作符
 */
public class RetrofitLoginActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitLoginActivity";
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_login);
        ButterKnife.bind(this);


        Observable<CharSequence> observableAccount = RxTextView.textChanges(etAccount);
        Observable<CharSequence> observablePwd = RxTextView.textChanges(etPwd);

        Observable.combineLatest(observableAccount, observablePwd,
                (phone, pwd) -> isPhoneValid(phone.toString()) && isPasswordValid(pwd.toString()))
                .subscribe(aBoolean -> {
                    Log.e(TAG, "" + aBoolean);
                    button.setEnabled(aBoolean);
                });

        RxView.clicks(button).throttleFirst(1, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> Log.e(TAG, "多个状态监听"));
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}
