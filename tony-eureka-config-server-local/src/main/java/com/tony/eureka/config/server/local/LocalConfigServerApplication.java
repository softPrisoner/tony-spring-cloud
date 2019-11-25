package com.tony.eureka.config.server.local;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 * @describe LocalConfigServerApplication
 * @date 2019-10-26
 */
@EnableEurekaClient
@EnableConfigServer
public class LocalConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocalConfigServerApplication.class, args);
    }
}
