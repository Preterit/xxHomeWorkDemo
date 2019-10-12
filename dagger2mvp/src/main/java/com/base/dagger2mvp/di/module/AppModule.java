package com.base.dagger2mvp.di.module;


import android.app.Application;

import com.base.dagger2mvp.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    public AppModule(App application) {
        this.mApplication = application;
    }

    private Application mApplication;

//    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }
}
