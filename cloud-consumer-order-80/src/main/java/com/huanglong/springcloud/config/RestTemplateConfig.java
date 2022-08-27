package com.huanglong.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**RestTemplate提供了多种便捷访问远程Http服务的方法，
 是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集
 类似 jdbcTemplate
  下面是进行配置
 *
 */
@Configuration
public class RestTemplateConfig {
    /*放入容器中*/
    /* 负载均衡  底层是轮询模式  一人一次轮换  */
//    @LoadBalanced //开启负载均衡 注销表示自己手写了一个负载算法 在 lb包中
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
