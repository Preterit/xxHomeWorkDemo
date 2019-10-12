package com.base.dagger2mvp.di.module;


import com.base.dagger2mvp.ui.di.LoginActivityModule;
import com.base.dagger2mvp.ui.di.RegisterActivityModule;
import com.base.dagger2mvp.ui.login.LoginActivity;
import com.base.dagger2mvp.ui.register.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AllActivitiesModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivityInjector();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity contributeRegisterActivityInjector();


}
