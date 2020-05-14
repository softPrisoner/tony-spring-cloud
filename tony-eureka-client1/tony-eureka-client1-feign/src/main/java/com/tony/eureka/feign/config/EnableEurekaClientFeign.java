package com.tony.eureka.feign.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EnableEurekaClient1Feign
 * @date 2020-05-14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EurekaClientConfiguration.class)
public @interface EnableEurekaClientFeign {
}
