package client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 * @describe EurekaClient2Application
 * @date 2019-10-26
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2Application.class, args);
    }
}
