package com.huanglong.springcloud.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.service.IPaymentService;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    IPaymentService paymentService;

    @GetMapping("/payment/sentinel/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Integer id){
        Payment payment = paymentService.getById(id);
        log.info("serverPort为:{}",serverPort);
        if (payment==null){
            return new CommonResult<Payment>(404,"没有这个人的信息",null);
        }else {
            return new CommonResult<Payment>(200,"这个人的信息是:",payment);
        }

    }

}
