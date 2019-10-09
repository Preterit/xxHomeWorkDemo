package com.xiangxue.dagger2.Scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
public class Engine {

    private String name;

    /**
     * @Scope<---->用于自定义注解,我们可以通过@Scope注解来限定注解作用域,实现局部单例;
     * @Scope<---->使用@Scope定义一个CarScope注解
     */

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CarScope {

    }

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
