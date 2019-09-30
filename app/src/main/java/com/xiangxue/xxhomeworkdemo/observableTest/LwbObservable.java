package com.xiangxue.xxhomeworkdemo.observableTest;

import java.util.Observable;

/**
 * Date:2019-09-19
 * author:lwb
 * Desc:
 */
public class LwbObservable extends Observable {

    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
