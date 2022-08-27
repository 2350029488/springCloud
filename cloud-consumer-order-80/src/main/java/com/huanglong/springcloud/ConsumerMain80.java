package com.huanglong.springcloud;

import com.huanglong.myrule.MyselfRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


/**
 *@EnableEurekaClient 将其注入进eurekaServer注册中心 ，成为服务消费者 好消费8001
 *@RibbonClient 修改负载均衡默认算法 name为注册中心服务提供者服务名称  configuration为修改算法的配置文件
 *
 * 排除掉数据库自动加载exclude = DataSourceAutoConfiguration.class
 */
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MyselfRuleConfig.class)
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain80.class,args);
    }
}
