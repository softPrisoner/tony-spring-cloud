package com.tony.eureka.trace.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tony
 * @describe TraceController
 * @date 2019/11/26
 */
@Controller
public class TraceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceController.class);
    @GetMapping("trace2")
    @ResponseBody
    public String trace2(){
        LOGGER.info("trace-1===>trace-2");
        return "TRACE-SERVER-2";
    }


}
