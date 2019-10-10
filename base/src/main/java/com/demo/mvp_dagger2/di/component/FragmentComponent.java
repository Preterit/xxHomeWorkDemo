package com.demo.mvp_dagger2.di.component;

import android.app.Activity;

import com.demo.mvp_dagger2.di.module.FragmentModule;
import com.demo.mvp_dagger2.di.socpe.FragmentScope;
import com.demo.mvp_dagger2.ui.HomeFragment;

import dagger.Component;

/**
 * 注射器
 * 可以理解为快递员，那么他需要送的货物就是modules里面包含的包裹
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(HomeFragment fragment);
}
