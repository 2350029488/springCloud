package com.huanglong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.handler.TestBloakHandler;
import com.huanglong.springcloud.handler.TestFallback;
import com.huanglong.springcloud.service.PaymentService;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {
    public static final String PAYMENT_URL="http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("/order/sentinel/{id}")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责异常
//    @SentinelResource(value = "fallback",blockHandler = "bloakHandler")//blockHandler只负责sentinel控制台的配置

    @SentinelResource(value = "fallback",
            blockHandler = "bloakHandler",blockHandlerClass= TestBloakHandler.class,
            fallback = "handlerFallback" ,fallbackClass = TestFallback.class)
    public CommonResult<Payment> test(@PathVariable("id")Integer id){

        CommonResult result = restTemplate.getForObject(PAYMENT_URL + "/payment/sentinel/" + id, CommonResult.class);
        if(result.getCode()==404){
            throw new IllegalArgumentException(" 没有这个人，非法参数异常 ");
        }
        return result;
    }

//   //本案例是blockHandler
//    public CommonResult<Payment> bloakHandler(Integer id, BlockException blockException){
//        Payment payment=new Payment(id.longValue(),"null");
//        return new CommonResult<>(445,"sentinel限流，异常为："+blockException.getMessage(),payment);
//    }
//
//    //本例是fallback
//    public CommonResult<Payment> handlerFallback(Integer id,Throwable e){
//        Payment payment=new Payment(id.longValue(),"null");
//        return new CommonResult<>(444,"兜底异常内容"+e.getMessage(),payment);
//
//    }


    //==================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Integer id)
    {
        CommonResult commonResult = paymentService.paymentSQL(id);
        if(commonResult.getCode()==404){
            throw new IllegalArgumentException(" 没有这个人，非法参数异常 ");
        }
        return commonResult;
    }

}
