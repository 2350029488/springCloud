package com.huanglong.springcloud;

import com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient  /** 需要排除SeataFegin的自动导入 不然注解全局回滚无法远程调用  */
@SpringBootApplication(exclude ={SeataFeignClientAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class SeataOrderMain2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class,args);
    }
}
