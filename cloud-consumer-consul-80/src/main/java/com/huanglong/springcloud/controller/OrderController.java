package com.huanglong.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    public static final  String PAYMENT_URL="http://consul-provider-payment";

    @GetMapping("/consul")
    public String getPayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
