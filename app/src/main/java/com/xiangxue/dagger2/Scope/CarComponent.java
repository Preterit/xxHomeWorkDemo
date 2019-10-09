package com.xiangxue.dagger2.Scope;

import dagger.Component;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 *
 * @Engine.CarScope<------->3.同时还需要使用@Scope去标注注入器Compoent
 */
@Engine.CarScope
@Component(modules = MarkCarModule.class)
public interface CarComponent {
    void inject(Car car);
}
