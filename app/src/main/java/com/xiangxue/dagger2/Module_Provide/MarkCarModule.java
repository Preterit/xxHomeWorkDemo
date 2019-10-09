package com.xiangxue.dagger2.Module_Provide;

import dagger.Module;
import dagger.Provides;

/**
 * @Module:用于标注提供依赖的类,区别于@Inject的是:需要提供依赖的构造函数时第三方库,没办法给加上@Inject,还有提供的构造函数带参数,参数传递是个问题,所以使用@Module很好的解决了这个问题
 */
@Module
public class MarkCarModule {
    public MarkCarModule() {
    }

    /**
     * @Provides:用于标注@Module所标注的类的方法,该方法在需要提供依赖的时候被调用,从而把预先提供好的对象当做依赖给标注了@Inject的变量赋值
     * @return
     */
    @Provides
    Engine provideEngine(){
        return new Engine("gear");
    }
}
