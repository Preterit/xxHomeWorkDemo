package com.xiangxue.dagger2.Module_Provide;

import javax.inject.Inject;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
public class Engine {

    @Inject
    public Engine() {
    }

    public Engine(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }

    public void run() {
        System.out.println("引擎转起来了~~~");
    }
}
