package com.tony.eureka.feign.hystrix;

import com.tony.eureka.feign.client.EurekaClientFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EurekaClientFeignClientFallbackFactory
 * @date 2020-05-14
 */
@Slf4j
public class EurekaClientFeignClientFallbackFactory implements FallbackFactory<EurekaClientFeignClient> {

    @Override
    public EurekaClientFeignClient create(Throwable cause) {
        log.error("EurekaClientFeignClient ERROR", cause);
        return new com.tony.eureka.feign.hystrix.EurekaClientFeignClientFallback(cause);
    }

}

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description EurekaClientFeignClientFallback
 * @date 2020-05-14
 */
@AllArgsConstructor
class EurekaClientFeignClientFallback implements EurekaClientFeignClient {
    @SuppressWarnings("unused")
    private final Throwable throwable;

    @Override
    public String hystrixTest(String businessId, String message) {
        return "ERROR";
    }
}
