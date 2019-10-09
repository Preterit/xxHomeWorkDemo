package com.xiangxue.dagger2.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
public class Engine {

    /**
     * 1.使用@Qualifier定义两个注解------在Engine类中
     */

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierA {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierB {
    }

    private String name;

    Engine(String name) {
        this.name = name;
        System.out.println("Engine create: " + name);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }
}
