package com.huanglong.springcloud.controller;

import com.huanglong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
/*服务降级全局配置 只要是注解@HystrixCommand并且没有指明方法的，出现错误都会调用该方法payment_Global_FallbackMethod */
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class ConsumerController {
    @Autowired
    private PaymentService paymentService;

    @HystrixCommand
    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
//        int xxx=10/0;
        return paymentService.paymentInfo_OK(id);
    }
    /* 在测试客户端服务降级时服务端睡眠时间要短，不然openFeign调用接口时超时出错*/
    @HystrixCommand(fallbackMethod="paymentInfoTimeOutFallbackMethod",commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
       int timeNumber=20;
       try { TimeUnit.MILLISECONDS.sleep(timeNumber); } catch (InterruptedException e) { e.printStackTrace(); }
        return paymentService.paymentInfo_TimeOut(id);
    }
    public String paymentInfoTimeOutFallbackMethod(Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o+id="+id;
    }
    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
