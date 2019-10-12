package com.demo.mvp_dagger2.di.component;

import android.app.Activity;

import com.demo.mvp_dagger2.di.module.ActivityModule;
import com.demo.mvp_dagger2.di.socpe.ActivityScope;
import com.demo.mvp_dagger2.ui.login.LoginActivity;
import com.demo.mvp_dagger2.ui.register.RegisterActivity;

import dagger.Component;

/**
 * 注射器
 * 可以理解为快递员，那么他需要送的货物就是modules里面包含的包裹
 */
@ActivityScope
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();
    /**
     * 把Component理解为快递员，那么他把包裹送给谁呢
     * 这里的inject方法的LoginActivity 就是送货的地址
     *
     * @param activity 目标容器
     * inject的参数。。。不能是父类，必须是你注入的那个内
     */
    void inject(LoginActivity activity);
    void inject(RegisterActivity activity);

}
