package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableHystrixDashboard 开启hystrix仪表盘工作
 */
@EnableHystrixDashboard
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HystrixDashboradMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboradMain9001.class,args);
    }
}
