package com.tony.eureka.feign.client;

import com.tony.eureka.feign.config.EurekaClientFeignConfig;
import com.tony.eureka.feign.hystrix.EurekaClientFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EurekaClientFeign
 * @date 2020-05-14
 */
@FeignClient(value = "eureka-client1", fallbackFactory = EurekaClientFeignClientFallbackFactory.class, configuration = EurekaClientFeignConfig.class)
public interface EurekaClientFeignClient {

    @GetMapping("/test/hystrix")
    String hystrixTest(@RequestParam String businessId, @RequestParam String message);
}
