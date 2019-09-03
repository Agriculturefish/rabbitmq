package com.yuhao.web.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SubToken {
    boolean saveToken() default false;
    boolean removeToken() default false;
}
