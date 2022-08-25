package com.huanglong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka包含两个组件：Eureka Server和Eureka Client
 * Eureka Server提供服务注册服务
 * 各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中
 * 将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。
 * EurekaClient通过注册中心进行访问
 * 是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、
 * 使用轮询(round-robin)负载算法的负载均衡器。在应用启动后，
 * 将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期
 * 内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除（默认90秒）
 *
 *  一定要标清哪个是注册服务 @EnableEurekaServer
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
