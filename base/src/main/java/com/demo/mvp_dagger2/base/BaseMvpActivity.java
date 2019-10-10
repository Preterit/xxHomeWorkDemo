package com.demo.mvp_dagger2.base;

import android.os.Bundle;

import com.demo.mvp_dagger2.App;
import com.demo.mvp_dagger2.di.component.ActivityComponent;
import com.demo.mvp_dagger2.di.component.DaggerActivityComponent;
import com.demo.mvp_dagger2.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.Unbinder;

public abstract class BaseMvpActivity<p extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject // @Inject作用一:用来标记以来的变量
    protected p mPresenter;
    protected App mApplication;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApplication = (App) getApplication();
        ComponentInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        if (mPresenter != null) mPresenter.onDestroy();
        this.mPresenter = null;
        this.mUnbinder = null;
        this.mApplication = null;
    }

    public abstract void ComponentInject();

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent
                .builder()
                .appComponent(mApplication.getmAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
}
