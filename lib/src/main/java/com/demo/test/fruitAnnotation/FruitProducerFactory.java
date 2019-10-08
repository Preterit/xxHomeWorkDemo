package com.demo.test.fruitAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :  lwb
 * Date: 2019/10/8
 * Desc: 水果生产工厂
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProducerFactory {

    /**
     * 水果商品编号
     */
    int id() default -1;

    /**
     * 水果工厂名称
     */
    String name() default "";

    /**
     * 水果工厂地址
     */
    String address() default "";

}
