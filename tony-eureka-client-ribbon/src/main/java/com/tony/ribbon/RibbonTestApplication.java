package com.tony.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe RibbonTestApplication
 * @date 2019-10-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonTestApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
