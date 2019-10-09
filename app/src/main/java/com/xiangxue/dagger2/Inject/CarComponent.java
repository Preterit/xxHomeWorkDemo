package com.xiangxue.dagger2.Inject;

import dagger.Component;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 * @Component:用来标注接口,是依赖需求方和依赖提供方之间的桥梁,被Component标注的接口会在编译的时候生成该接口的实现类, (如果 @ Component标注的接口为CarComponent, 则编译期生成的实现类为DaggerCarComponent), 我们通过调用这个实现类的方法完成注入
 */
@Component
public interface CarComponent {
    void inject(Car car);
}
