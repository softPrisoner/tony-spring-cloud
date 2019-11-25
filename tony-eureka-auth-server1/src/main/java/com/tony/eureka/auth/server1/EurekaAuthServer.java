package com.tony.eureka.auth.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author tony
 * @version 1.0
 * @description 网关鉴权业务逻辑
 * @date 2019-07-24
 */
@SpringCloudApplication
//添加鉴权服务器注解
@EnableAuthorizationServer
public class EurekaAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaAuthServer.class, args);
    }
}
