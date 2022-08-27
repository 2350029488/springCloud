package com.huanglong.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-24
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 只是简单的测试
     */
    @RequestMapping("/consul")
    public String paymentConsul(){
        return "springcloud with consul"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
