package com.huanglong.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "8001服务器宕机+id:"+id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "8001服务器宕机+id:"+id;
    }
}
