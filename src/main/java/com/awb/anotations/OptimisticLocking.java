package com.awb.anotations;

import java.lang.annotation.*;

/**
 * Created by asus on 2018/8/22.
 */

@Target(value = {ElementType.PARAMETER,         ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptimisticLocking {
    String description() default "";
}
