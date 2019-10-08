package com.demo.test;

import com.demo.test.fruitAnnotation.Apple;
import com.demo.test.humen.ChildForZsPeo;
import com.demo.test.tools.FruitTools;

/**
 * @author :  lwb
 * Date: 2019/10/8
 * Desc:
 */
public class FruitMain {
    public static void main(String[] args) {
//        fruitMethod();   // 字段注解
        peoMethod();     // 注解继承
    }

    public static void fruitMethod(){
        FruitTools.getFruitInfo(Apple.class);
    }

    public static void peoMethod(){
        FruitTools.getFruitInfo(ChildForZsPeo.class);
    }
}
