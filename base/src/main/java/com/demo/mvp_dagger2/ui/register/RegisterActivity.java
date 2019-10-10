package com.demo.mvp_dagger2.ui.register;

import com.demo.mvp_dagger2.R;
import com.demo.mvp_dagger2.base.BaseMvpActivity;
import com.demo.mvp_dagger2.ui.HomeFragment;
import com.demo.mvp_dagger2.utils.UIUtils;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> {

    @Override
    public int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void ComponentInject() {

    }

    @Override
    public void initData() {
        HomeFragment loginFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);
        if (loginFragment == null) {
            loginFragment = HomeFragment.newInstance();
            UIUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.contentFrameLayout);
        }
    }
}
