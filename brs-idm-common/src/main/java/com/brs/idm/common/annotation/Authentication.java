package com.brs.idm.common.annotation;

import java.lang.annotation.*;

/**
 * @className Authentication
 * @description 权限认证注解标志
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {
}
