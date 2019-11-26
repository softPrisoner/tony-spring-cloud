package com.tony.eureka.trace.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe TraceApplication1
 * @date 2019/11/26
 */
@SpringBootApplication
public class TraceApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(TraceApplication1.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
