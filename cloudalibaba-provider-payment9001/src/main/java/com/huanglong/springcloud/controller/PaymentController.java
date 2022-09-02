package com.huanglong.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    IPaymentService paymentService;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id")Integer id){

        Payment payment = paymentService.getById(id);
        return "serverPort:"+serverPort+"\t"+"\t"+"payment:"+payment.toString();
    }

}
