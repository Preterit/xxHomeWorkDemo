package com.xiangxue.xxhomeworkdemo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Date:2019-09-10
 * author:lwb
 * Desc:  代理类
 */
public class LwbCompany implements InvocationHandler {

    public Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        doSthAfter();
        Object result = method.invoke(factory, objects);
        doSthBefore();
        return result;
    }

    /*前置处理器*/
    private void doSthAfter() {
        System.out.println("精美包装，快递一条龙服务");
    }
    /*后置处理器*/

    private void doSthBefore() {
        System.out.println("根据需求，进行市场调研和产品分析");
    }

}
