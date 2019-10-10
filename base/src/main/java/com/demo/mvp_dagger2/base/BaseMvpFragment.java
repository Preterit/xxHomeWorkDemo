package com.demo.mvp_dagger2.base;

import android.os.Bundle;

import com.demo.mvp_dagger2.App;
import com.demo.mvp_dagger2.di.component.DaggerFragmentComponent;
import com.demo.mvp_dagger2.di.component.FragmentComponent;
import com.demo.mvp_dagger2.di.module.FragmentModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    @Inject
    protected P mPresenter;
    private App mApplication;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApplication = (App) getActivity().getApplication();
        ComponentInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
        super.onDestroy();
    }

    public abstract void ComponentInject();

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent
                .builder()
                .appComponent(mApplication.getmAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }
}
