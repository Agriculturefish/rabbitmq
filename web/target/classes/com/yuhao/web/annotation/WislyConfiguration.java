package com.yuhao.web.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Inherited
// @ComponentScan("com.yuhao.business.annotation")
@ComponentScan
public @interface WislyConfiguration {
    String[] value() default {};
}
