package com.rainbow.tony.eureka.hystrix.service;

/**
 * @author tony
 * @describe HystrixConsumer
 * @date 2019-11-10
 */
public interface HystrixConsumeService {

    String consumeMessage(String businessId, String message);

    String fallback(String businessId, String message) ;
}
