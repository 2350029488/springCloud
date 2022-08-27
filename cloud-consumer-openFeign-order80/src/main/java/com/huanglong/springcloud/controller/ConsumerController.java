package com.huanglong.springcloud.controller;

import com.huanglong.springcloud.service.PaymentFeignService;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{payId}")
   public CommonResult getPaymentById(@PathVariable("payId")Long payId){

        /** 调用服务绑定接口的方法，实现调用 */
    return     paymentFeignService.getPaymentById(payId);
    }


    /*测试openFeign连接超时*/
    @GetMapping("/runTimeout")
    public String runTimeout(){
        return paymentFeignService.runTimeout();
    }

}
