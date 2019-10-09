package com.xiangxue.dagger2.Scope;

import dagger.Module;
import dagger.Provides;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
@Module
public class MarkCarModule {

    public MarkCarModule() {
    }

    @Engine.CarScope
    @Provides
    Engine provideEngine(){
        return new Engine("gear");
    }
}
