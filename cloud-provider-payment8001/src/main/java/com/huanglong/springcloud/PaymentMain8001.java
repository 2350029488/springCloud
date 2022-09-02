package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@EnableEurekaClient 将其注入进eurekaServer注册中心 ，成为服务提供者provider   好给消费者（80）使用
 @EnableDiscoveryClient /*服务发现Discovery
 */
@EnableDiscoveryClient /*服务发现Discovery 这里开不开起影响不大*/
@EnableEurekaClient
@SpringBootApplication()
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
