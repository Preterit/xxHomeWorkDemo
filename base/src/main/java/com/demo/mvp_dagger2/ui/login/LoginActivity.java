package com.demo.mvp_dagger2.ui.login;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.mvp_dagger2.R;
import com.demo.mvp_dagger2.base.BaseMvpActivity;
import com.demo.mvp_dagger2.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void ComponentInject() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.tv_btn)
    public void onViewClicked() {
        mPresenter.showMsg();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(mApplication, "请求成功", Toast.LENGTH_SHORT).show();
        Log.e(TAG,msg);
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
