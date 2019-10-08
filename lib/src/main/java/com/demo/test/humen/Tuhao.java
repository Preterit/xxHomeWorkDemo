package com.demo.test.humen;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author :  lwb
 * Date: 2019/10/8
 * Desc:
 */
@Inherited   // 注解继承
@Retention(RetentionPolicy.RUNTIME)
public @interface Tuhao {
    String value() default "土豪";
}
