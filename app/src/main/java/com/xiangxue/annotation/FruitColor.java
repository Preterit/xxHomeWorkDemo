package com.xiangxue.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :  lwb
 * Date: 2019/10/8
 * Desc: 水果颜色注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色
     */
    enum Color {BLUE,RED,GREEN,YELLOW}

    /**
     * 颜色属性
     */
    Color fruitColor() default Color.BLUE;

}
