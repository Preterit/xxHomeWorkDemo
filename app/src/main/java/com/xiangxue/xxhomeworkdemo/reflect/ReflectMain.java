package com.xiangxue.xxhomeworkdemo.reflect;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class ReflectMain {

    /**
     * ThreadLocal 线程隔离
     * 使用情景: 当线程需要操作上一个线程修改的完的数据,为了保证数据的准确性,
     */
    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @SuppressLint("StaticFieldLeak")
    private static AsyncTask task = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    };

    public static void main(String[] args) {
        LwbCompany lwbCompany = new LwbCompany();  // 创建代理类
        TicketsService service = new TicketsServiceImpl();
        lwbCompany.setFactory(service);

        TicketsService proxyInstance = (TicketsService) lwbCompany.getProxyInstance();
        proxyInstance.inquire();
        task.execute();
    }
}

