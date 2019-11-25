package com.tony.eureka.zuul;

import com.tony.eureka.zuul.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * ××××××××业务逻辑
 * <pre>
 * 修改日期                   修改人  修改原因
 * 2019-11-24    LDD    新建
 * </pre>
 */
@EnableZuulProxy
@SpringCloudApplication
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class TonyZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(TonyZuulApplication.class, args);
    }

    @Bean
    public AccessFilter getAccessFilter() {
        return new AccessFilter();
    }
}
