package com.wxw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 9:33 2017/8/1
 * @Modified By :
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBLog {
    String value() default "";
}

