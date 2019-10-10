package com.demo.mvp_dagger2;

import android.app.Application;

import com.demo.mvp_dagger2.di.component.AppComponent;
import com.demo.mvp_dagger2.di.component.DaggerAppComponent;
import com.demo.mvp_dagger2.di.module.AppModule;


public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getmAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return mAppComponent;
    }
}
