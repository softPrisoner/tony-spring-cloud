package com.tony.feign.controller;

import com.tony.feign.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tony
 * @describe TestController
 * @date 2019-10-26
 */
@RestController
@RequestMapping("/feign")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
        return testService.test(id);

    }
}
