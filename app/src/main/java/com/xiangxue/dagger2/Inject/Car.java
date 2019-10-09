package com.xiangxue.dagger2.Inject;

import javax.inject.Inject;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 * <p>
 * Car类是需求的依赖方,依赖了Engine()类; 因此我们需要在变量Engine上添加@Inject来告诉dagger2来为自己提供依赖
 */
public class Car {
    /**
     * @Inject:有两个作用,一是用来标记需要依赖的变量,以此告诉Dagger2为它提供依赖
     */
    @Inject
    Engine engine;

    public Car() {
        DaggerCarComponent.builder().build().inject(this);
    }

    public Engine getEngine() {
        return this.engine;
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getEngine());
    }
}
