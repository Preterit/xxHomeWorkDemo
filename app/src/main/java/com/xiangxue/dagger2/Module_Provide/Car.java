package com.xiangxue.dagger2.Module_Provide;

import javax.inject.Inject;

/**
 * 如果创建Engine是带参数的,比如制造一台引擎是需要齿轮(Gear)的,或者Engine类是我们无法修改的,这时候就需要@Module和@Provide上场了
 */
public class Car {

    /**
     * 我们提到@Inject和@Module都可以提供依赖,那么我们即在构造函数上通过标记@Inject提供依赖,又通过@Module提供依赖,Dagger2会如何进行取舍,具体规则如下:
     *
     * 步骤1 : 首先查找@Module标注的类中是否存在提供依赖的方法;
     * 步骤2 : 若存在提供依赖的方法,查看该方法是否存在参数;
     * a : 若存在参数,从步骤1开始依次初始化每个参数
     * b : 若不存在参数,则直接初始化该类实例,完成一次依赖注入
     *
     * 步骤3 : 若不存在提供依赖的方法,则查看@Inject标注的构造函数,看构造函数是否存在参数;
     * a : 若存在参数,从步骤1开始依次初始化每个参数;
     * b : 若不存在参数,则直接初始化该类实例,完成一次注入;
     */

    @Inject
    Engine engine;

    public Car() {
        DaggerCarComponent.builder().markCarModule(new MarkCarModule()).build().inject(this);
    }

    public Engine getEngine() {
        return this.engine;
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getEngine());
    }
}
