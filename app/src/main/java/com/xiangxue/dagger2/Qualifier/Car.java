package com.xiangxue.dagger2.Qualifier;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 如果一台汽车有两台引擎(也就是说Car类有两个Engine变量),怎么办?,使用@Qualifier
 */
public class Car {

    /**
     * @Qualifier:----用于自定义注解,也就是说@Qualifier就如同Java提供的几种基本元注解一样用来标记注解类;
     * @Qualifier:----我们在使用@Module来标注提供依赖的方法时,方法名我们是可以随便定义的(虽然我们定义方法名一般以provide开头,但这不是强制的,只是为了增加可读性而已);
     * @Qualifier:----那么Dagger2怎么知道这个方法是为谁提供依赖呢?答案就是返回值类型,Dagger2根据返回值类型来决定为那个被@Inject标注的变量赋值;
     * @Qualifier:----那么问题来了,一旦有多个一样的返回值类型Dagger2就懵逼了;
     * @Qualifier:----@Qualifier的存在正是为了解决这个问题,我们使用@Qualifier来定义自己的注解;
     * @Qualifier:----一个更为精简的定义:当类型不足以鉴别一个依赖的时候,我们就可以使用这个注解表示
     *
     * @Qualifier:----步骤:
     * @Qualifier:----1.使用@Qualifier定义两个注解------在Engine类中
     * @Qualifier:----2.同时我们需要对依赖的提供方做出修改------在MarkCarModule类中
     * @Qualifier:----3.接下来依赖需求方同样需要修改-----在Car类中
     */

    /**
     * 3.接下来依赖需求方同样需要修改-----在Car类中
     *
     * @Engine.QualifierB<----等价于---->@Named("a")
     */
    //    @Engine.QualifierA
    @Named("a")
    @Inject
    Engine engineA;

    //    @Engine.QualifierB
    @Named("b")
    @Inject
    Engine engineB;

    public Car() {
        DaggerCarComponent.builder().markCarModule(new MarkCarModule()).build().inject(this);
    }

    public Engine getEngineA() {
        return this.engineA;
    }

    public Engine getEngineB() {
        return this.engineB;
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getEngineA());
        System.out.println(car.getEngineB());
    }
}
