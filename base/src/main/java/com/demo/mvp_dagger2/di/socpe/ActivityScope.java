package com.demo.mvp_dagger2.di.socpe;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
