package com.xiangxue.dagger2.Inject;

import javax.inject.Inject;

/**
 * @author :  lwb
 * Date: 2019/10/9
 * Desc:
 */
public class Engine {

    /**
     * @Inject:用来标记构造函数时:Dagger2通过@Inject这个注解可以在需要这个类实例的时候来找到这个构造函数并把相关实例创建出来,以此来为被@Inject标记了的变量提供依赖
     */
    @Inject
    public Engine() {
    }

    private String name;

    @Override
    public String toString() {
        return "Engine{}";
    }

    public void run() {
        System.out.println("引擎转起来了~~~");
    }
}
