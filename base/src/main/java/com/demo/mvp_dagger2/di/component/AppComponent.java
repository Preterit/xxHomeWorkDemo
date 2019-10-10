package com.demo.mvp_dagger2.di.component;

import com.demo.mvp_dagger2.App;
import com.demo.mvp_dagger2.di.module.AppModule;
import com.demo.mvp_dagger2.network.api.DemoApi;
import com.demo.mvp_dagger2.network.di.ApiServiceModule;
import com.demo.mvp_dagger2.network.di.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 注射器
 * 可以理解为快递员，那么他需要送的货物就是modules里面包含的包裹
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, ApiServiceModule.class})
public interface AppComponent {
    App application();

    DemoApi getDemoApi();
}
