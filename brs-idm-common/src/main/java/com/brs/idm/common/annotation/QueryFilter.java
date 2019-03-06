package com.brs.idm.common.annotation;

import java.lang.annotation.*;

/**
 * @className QueryFilter
 * @description TODO
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface QueryFilter {
    String voName() default  "";
}
