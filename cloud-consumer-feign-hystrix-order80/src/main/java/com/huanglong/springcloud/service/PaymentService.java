package com.huanglong.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通配服务降级FeignFallback  fallback =PaymentHystrixServiceImpl.class
 * 其实就是服务器宕机后的回退处理，服务器挂机后，那么在注册中心都没有服务名称了，
 * 在连接客户端和服务器的接口中通过@FeignClient注解的fallback属性值（值为实现类且实现连接接口）
 * 到这个值（实现类中），通过访问的是哪个方法就返回哪个方法的内容
 */
@Component
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback =PaymentHystrixServiceImpl.class )
public interface PaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
