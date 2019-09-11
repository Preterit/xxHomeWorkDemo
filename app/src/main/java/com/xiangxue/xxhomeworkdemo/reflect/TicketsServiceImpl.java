package com.xiangxue.xxhomeworkdemo.reflect;

/**
 * @author :  lwb
 * Date: 2019/9/11
 * Desc:  相当于车票代售点
 */
public class TicketsServiceImpl implements TicketsService {

    @Override
    public void sellTicket() {
        System.out.println("\n\t售票.....\n");
    }

    @Override
    public void inquire() {
        System.out.println("\n\t问询。。。。\n");
    }

    @Override
    public void withdraw() {
        System.out.println("\n\t退票......\n");
    }

}
