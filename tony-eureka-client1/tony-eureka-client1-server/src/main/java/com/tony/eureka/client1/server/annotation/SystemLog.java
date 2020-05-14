package com.tony.eureka.client1.server.annotation;


import com.tony.eureka.client1.server.common.Dictionary;

import java.lang.annotation.*;

/**
 * 系统日志切面注解
 *
 * @author tony
 * @describe SystemLog
 * @date 2019-08-18
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SystemLog {

    String businessName() default "";

    String functionCode() default "";

    String functionName() default "";

    Dictionary.AccessType accessType() default Dictionary.AccessType.WRITE;

    Dictionary.LogLevel logLevel() default Dictionary.LogLevel.INFO;
}
