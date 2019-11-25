package com.tony.eureka.client1.controller;

import com.tony.eureka.client1.annotation.SystemLog;
import com.tony.eureka.client1.common.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @description TestController
 * @date 2019-09-11
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    @SystemLog(businessName = "支付接口", functionName = "测试Hystrix超时", functionCode = "TEST-01-01", accessType = Dictionary.AccessType.READ)
    public String test() throws InterruptedException {
        //这里超时设置
        Thread.sleep(10000);
        LOGGER.info("invoke test() from client1");
        return "hello word";
    }

    @GetMapping("/hystrix")
    public String hystrixTest(@RequestParam String businessId, @RequestParam String message) {
        return businessId + "-" + message;
    }
}
