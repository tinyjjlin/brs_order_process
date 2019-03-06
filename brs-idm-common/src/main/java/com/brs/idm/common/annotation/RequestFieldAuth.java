package com.brs.idm.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className RequestFieldAuth
 * @description TODO
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestFieldAuth {
    String dtoName() default "";
}
