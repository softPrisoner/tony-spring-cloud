package com.tony.feign.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tony
 * @describe TestClient
 * @date 2019-07-26
 */
@FeignClient("eureka-client1")
public interface TestClient {
    @GetMapping("/test")
    String test();
}
