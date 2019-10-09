package com.xiangxue.dagger2.Module_Provide;

import dagger.Component;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
@Component(modules = MarkCarModule.class)
public interface CarComponent {
    void inject(Car car);
}
