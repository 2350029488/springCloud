package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication()
public class SintinelPaymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(SintinelPaymentMain9004.class,args);
    }
}
