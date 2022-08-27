package com.huanglong.springcloud.service;

import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**我们只需创建一个接口并使用注解的方式来配置它
 * (以前是Dao接口上面标注Mapper注解,现在是一个微服务接口上面标注一个Feign
 * 注解即可)，即可完成对服务提供方的接口绑定*/
@Component
@FeignClient(name ="CLOUD-PAYMENT-SERVICE")/**name为在注册中心的服务名称*/
public interface PaymentFeignService {

    /** 服务提供者的接口，这里进行绑定,
     * 客户端需要调用是直接调用当前接口即可，当前接口又通过@FeignClient(name ="CLOUD-PAYMENT-SERVICE")注解里的name值
     * 指定的服务名称，就会通过轮询的方式找服务器（8001 8002）中对应的请求地址进行处理
     * 类似restTemplate.getForObject
     */
    @GetMapping("/payment/get/{payId}")
    public CommonResult getPaymentById(@PathVariable("payId")Long payId);



    /*测试openFeign连接超时*/
    @GetMapping("/payment/runTimeout")
    public String runTimeout();
}
