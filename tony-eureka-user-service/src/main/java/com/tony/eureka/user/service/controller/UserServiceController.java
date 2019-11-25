package com.tony.eureka.user.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tony
 * @version 1.0
 * @description ××××××××业务逻辑
 * @date 2019-07-24
 */
@Controller
public class UserServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceController.class);

    @RequestMapping("/login")
    public String login(String username, String password) throws IOException {
        if ("tony".equals(username) && "tony".equals(password)) {
            //HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            //response.addHeader("Location","http://localhost:8090/user-service/preLogin/");
            //response.sendRedirect("http://localhost:8090/user-service/preLogin/");
            LOGGER.info("username:[{}],password:[{}] login success", username, password);
            return "assignee_main.html";
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request.getParameter("accessToken");
        LOGGER.info("accessToken:[{}]", accessToken);
        return "redirect:/preLogin?accessToken=" + accessToken;
    }

    @RequestMapping("/preLogin")
    public String preLogin() {
        return "assignee_login.html";
    }
}
