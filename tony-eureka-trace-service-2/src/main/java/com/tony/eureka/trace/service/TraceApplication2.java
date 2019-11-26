package com.tony.eureka.trace.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe TraceApplication2
 * @date 2019/11/26
 */
@SpringBootApplication
public class TraceApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(TraceApplication2.class, args);
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
