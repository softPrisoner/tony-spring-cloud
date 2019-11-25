package com.rainbow.tony.eureka.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.rainbow.tony.eureka.hystrix.service.HystrixConsumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe HystrixConsumeServiceImpl
 * @date 2019-11-10
 */
@Service

public class HystrixConsumeServiceImpl implements HystrixConsumeService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixConsumeServiceImpl.class);

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    @CacheResult(cacheKeyMethod = "getCacheKey")
    public String consumeMessage(String businessId, String message) {
        return restTemplate.getForObject("http://eureka-client1/test/hystrix?businessId=" + businessId + "&message=" + message, String.class);
    }

    @Override
    public String fallback(String businessId, String message) {
        return "fallback";
    }

    public String getCacheKey(String seed) {
        LOGGER.info("the cache key is" + seed);
        return seed;
    }

}
