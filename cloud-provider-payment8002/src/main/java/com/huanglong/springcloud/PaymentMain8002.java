package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@EnableEurekaClient 将其注入进eurekaServer注册中心 ，成为服务提供者provider   好给消费者（80）使用
 */
@EnableEurekaClient
@SpringBootApplication()
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
