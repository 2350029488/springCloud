package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigClientMain3388 {
    public static void main(String[] args) {
            System.setProperty("spring.cloud.bootstrap.enabled","true");
        SpringApplication.run(ConfigClientMain3388.class,args);
    }
}
