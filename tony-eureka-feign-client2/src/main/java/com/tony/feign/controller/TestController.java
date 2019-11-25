package com.tony.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @describe TestController
 * @date 2019-10-26
 */
@RestController
@RequestMapping("/feign")
public class TestController {
    @Autowired
    TestClient testClient;

    @RequestMapping("/test/{id}")
    public String test(@PathVariable String id) {
        return testClient.test();

    }
}
