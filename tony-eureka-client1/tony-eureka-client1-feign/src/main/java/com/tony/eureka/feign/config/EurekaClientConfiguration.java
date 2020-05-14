package com.tony.eureka.feign.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EurekaClient1Configuration
 * @date 2020-05-14
 */
public class EurekaClientConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        // Guard against calls for sub-classes
        Map<String, Object> enableConfiguration = annotationMetadata.getAnnotationAttributes(getAnnotation().getName());
        if (enableConfiguration == null) {
            return;
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(EurekaClient1ServiceContext.class);
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        registry.registerBeanDefinition("eurekaClientFeignConfiguration", beanDefinition);
    }

    protected Class<? extends Annotation> getAnnotation() {
        return EnableEurekaClientFeign.class;
    }

    @Configuration
    @EnableFeignClients(basePackages = {"com.tony.eureka.feign.client"})
    @ComponentScan(basePackages = "com.tony.eureka.feign.client")
    public static class EurekaClient1ServiceContext {
    }

}
