package com.demo.mvp_dagger2.ui.register;

import com.demo.mvp_dagger2.base.RxPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public class RegisterPresenter extends RxPresenter<RegisterView> {

    @Inject
    public RegisterPresenter() {
    }

    public void showMsg(String msg) {
        getDemoApi().getProject().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(projectBean -> {
                    if (projectBean != null) {
                        mView.showData(projectBean);
                    }
                });
    }
}
