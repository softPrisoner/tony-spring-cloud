package com.tony.feign.service;

import com.tony.eureka.feign.client.EurekaClientFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wabslygzj@163.com (Tony Li)
 * @copyright rainbow
 * @description TestService
 * @date 2020-05-14
 */
@Service
public class TestService {

    @Resource
    private EurekaClientFeignClient test;

    public String test(String id) {
        return test.hystrixTest(id, "tony");
    }
}
