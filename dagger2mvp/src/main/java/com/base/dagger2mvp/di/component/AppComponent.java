package com.base.dagger2mvp.di.component;


import com.base.dagger2mvp.App;
import com.base.dagger2mvp.di.module.AllActivitiesModule;
import com.base.dagger2mvp.di.module.AppModule;
import com.base.dagger2mvp.network.api.WanAndroidApi;
import com.base.dagger2mvp.network.di.ApiServiceModule;
import com.base.dagger2mvp.network.di.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class
        , AndroidSupportInjectionModule.class
        , AllActivitiesModule.class
        , AppModule.class
        , HttpModule.class
        , ApiServiceModule.class})

public interface AppComponent {

    void inject(App application);

    WanAndroidApi getWanAndroidApi();
}
