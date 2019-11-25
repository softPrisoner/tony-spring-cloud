package client2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @describe TestController
 * @date 2019-10-26
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("")
    public String test() {
        LOGGER.info("invoke test() from client2");
        return "hello word";
    }
}
