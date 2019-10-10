package com.demo.mvp_dagger2.base;

import com.demo.mvp_dagger2.network.api.DemoApi;

import javax.inject.Inject;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    @Inject
    DemoApi demoApi;

    protected T mView;

    @Override
    public void attachView(T t) {
        this.mView = t;
    }

    @Override
    public void onDestroy() {
        if (mView != null) {
            mView = null;
        }
    }

    public DemoApi getDemoApi() {
        if (demoApi != null) {
            return demoApi;
        }
        return null;
    }

}
