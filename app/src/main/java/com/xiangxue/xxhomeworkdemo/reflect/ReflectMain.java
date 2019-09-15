package com.xiangxue.xxhomeworkdemo.reflect;

public class ReflectMain {

    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        LwbCompany lwbCompany = new LwbCompany();  // 创建代理类
        TicketsService service = new TicketsServiceImpl();
        lwbCompany.setFactory(service);

        TicketsService proxyInstance = (TicketsService) lwbCompany.getProxyInstance();
        proxyInstance.inquire();
    }
}

