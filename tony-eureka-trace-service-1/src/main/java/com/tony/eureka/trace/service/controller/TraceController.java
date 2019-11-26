package com.tony.eureka.trace.service.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @describe TraceController
 * @date 2019/11/26
 */
@Controller
public class TraceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceController.class);
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/trace1")
    @ResponseBody
    public String trace1() {
        LOGGER.info("==>trace1");
        String result = restTemplate.getForObject("http://TRACE-SERVICE-2/trace2", String.class);
            //p1:服务id
            //trace-id: 全局唯一
            //spanId: 调用接口唯一，全局不唯一
//        [trace-service-1,1f09350499d5163b,1f09350499d5163b,false]
        LOGGER.info("result==>{}", result);
        return JSON.toJSONString(result);
    }


}
