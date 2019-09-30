package com.xiangxue.xxhomeworkdemo.observableTest;

import java.util.Observable;
import java.util.Observer;

/**
 * Date:2019-09-19
 * author:lwb
 * Desc:
 */
public class ObservableMain {
    public static void main(String[] args) {
        Observable observable = new LwbObservable();
        Observer observer = new Whater();
        observable.addObserver(observer);


        observable.notifyObservers("有新的消息来了");
    }
}
