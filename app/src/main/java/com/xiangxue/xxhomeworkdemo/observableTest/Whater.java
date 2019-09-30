package com.xiangxue.xxhomeworkdemo.observableTest;

import java.util.Observable;
import java.util.Observer;

/**
 * Date:2019-09-19
 * author:lwb
 * Desc:
 */
public class Whater implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        System.out.println(o);
    }
}
