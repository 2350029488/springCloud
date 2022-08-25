package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 *@EnableEurekaClient 将其注入进eurekaServer注册中心 ，成为服务消费者 好消费8001
 *
 * 排除掉数据库自动加载
 */
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain80.class,args);
    }
}
