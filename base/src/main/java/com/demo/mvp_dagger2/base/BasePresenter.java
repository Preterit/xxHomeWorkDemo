package com.demo.mvp_dagger2.base;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public interface BasePresenter<T> {
    void attachView(T t);
    void onDestroy();
}
