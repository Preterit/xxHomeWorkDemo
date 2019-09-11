package com.xiangxue.xxhomeworkdemo.reflect;

public class ReflectMain {
    public static void main(String[] args) {
        LwbCompany lwbCompany = new LwbCompany();  // 创建代理类
        TicketsService service = new TicketsServiceImpl();
        lwbCompany.setFactory(service);

        TicketsService proxyInstance = (TicketsService) lwbCompany.getProxyInstance();
        proxyInstance.inquire();
    }
}

