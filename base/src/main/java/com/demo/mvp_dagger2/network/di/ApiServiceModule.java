package com.demo.mvp_dagger2.network.di;

import com.demo.mvp_dagger2.network.api.DemoApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
@Module
public class ApiServiceModule {

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse("https://www.wanandroid.com/");
    }

    @Singleton
    @Provides
    DemoApi provideDemoApi(Retrofit retrofit) {
        return retrofit.create(DemoApi.class);
    }
}
