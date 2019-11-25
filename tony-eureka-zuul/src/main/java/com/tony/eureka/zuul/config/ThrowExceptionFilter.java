package com.tony.eureka.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tony
 * @version 1.0
 * @description 全局异常处理业务逻辑
 * @date 2019-11-25
 */
public class ThrowExceptionFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        LOGGER.info("This is a pre filter, it will throw a RuntimeException");
        doSomeThing();
        return null;
    }

    private void doSomeThing() {
        throw new RuntimeException("some error happen");
    }
}
