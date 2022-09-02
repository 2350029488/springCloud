package com.huanglong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.myhandler.CustomerBlockHandler;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2020-02-25 15:04
 */
@RestController
public class RateLimitController
{
    /*测试sentinel控制台的流控模式为资源名 的测试*/
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

  /*兜底方法*/
    public CommonResult handleException(BlockException exception)
    {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }



    /*测试sentinel控制台的流控模式为url 的测试*/
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }


    /**
 测试全局配置兜底方法，进行统一的处理
  通过@SentinelResource的blockHandlerClass参数  设置统一处理类，
     通过blockHandler的值表示统一处理类中的哪个方法作为兜底，
     与Hystrix基本一致，只不过hystrix是在当前类上注解@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
     指明具体方法，Hytrix在处理服务器宕机的才在@FeignClient接口调用设置处理的类
     */

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }
}
