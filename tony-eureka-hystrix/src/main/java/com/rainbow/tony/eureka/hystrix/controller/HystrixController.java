package com.rainbow.tony.eureka.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rainbow.tony.eureka.hystrix.service.HystrixConsumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @describe HystrixController
 * @date 2019-07-26
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixController.class);
    @Autowired
    HystrixConsumeService hystrixConsumeService;

    //TODO consider fall back method with api or service
    // with api if user consume failed give the fallback tip
    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "fallback", commandKey = "test1", groupKey = "group1")
    public String consumeApi(@RequestParam String businessId, @RequestParam String message) {
        LOGGER.info("consume start");
        return hystrixConsumeService.consumeMessage(businessId, message);
    }

    //fall back method should match the params of command
    public String fallback(String businessId, String message) {
        LOGGER.info(businessId + "-" + message);
        return "some error happened";
    }
}
