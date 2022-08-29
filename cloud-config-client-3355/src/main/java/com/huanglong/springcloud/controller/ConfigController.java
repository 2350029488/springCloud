package com.huanglong.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试从配置服务中心3344中 获取配置文件信息
 */
@RestController
@RefreshScope//刷新功能
public class ConfigController {
    @Value("${config.info}")
    private String name;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/test")
    public String test(){
        return name+ "\t" +serverPort;
    }
}
