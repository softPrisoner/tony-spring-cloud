package com.tony.ribbon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe RibbonTestController
 * @date 2019-10-26
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonTestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonTestController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @GetMapping("/test")
    public String test() {
        String result = restTemplate.getForObject("EUREKA-CLIENT1/test", String.class);
        LOGGER.info("result:{}", result);
        return result;
    }

//    @Autowired
//    @GetMapping("/testPost")
//    public String testPost() {
//        ResponseEntity<String> entity = restTemplate.postForEntity("http://EUREKA-CLIENT1/testPost", null, String.class);
//        int status = entity.getStatusCodeValue();
//        String body = entity.getBody();
//        HttpHeaders headers = entity.getHeaders();
//        LOGGER.info("response code:{},body:{},headers:{}", status, body, headers.toString());
//        return "response code:" + status + "\nbody:" + body;
//    }

}
