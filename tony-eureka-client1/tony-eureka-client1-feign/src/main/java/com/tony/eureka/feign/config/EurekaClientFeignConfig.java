package com.tony.eureka.feign.config;

import feign.Request;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EurekaClientFeignConfig
 * @date 2020-05-14
 */
public class EurekaClientFeignConfig {
    @SuppressWarnings("unused")
    public Request.Options options() {
        int connectTimeOutMillis = 1800000;
        int readTimeOutMillis = 1800000;
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}