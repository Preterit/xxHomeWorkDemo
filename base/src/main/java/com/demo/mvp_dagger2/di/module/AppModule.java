package com.demo.mvp_dagger2.di.module;

import com.demo.mvp_dagger2.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
@Module
public class AppModule {

    private App mApplication;

    public AppModule(App mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public App provideApplication() {
        return mApplication;
    }
}
