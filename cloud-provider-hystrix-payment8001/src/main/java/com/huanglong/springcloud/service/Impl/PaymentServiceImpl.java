package com.huanglong.springcloud.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.huanglong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**********************************服务降级演示*************************************/
    /**********************************服务降级演示*************************************/
    /**
       正常访问 肯定OK
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:  "+Thread.currentThread().getName()+"  paymentInfo_OK,id:  "+id+"\t"+"O(∩_∩)O哈哈~";
    }
    /**非正常访问 * 超时访问或者异常，演示降级
     *  一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     *
     *    commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
     *    表示访问时间超过3秒就超时访问（因为设置睡眠的是4秒所以这里会超时），fallbackMethod调用类中的指定方法
     * */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String paymentInfo_TimeOut(Integer id) {
//        int age = 10/0;
        int timeNumber=1;          /* 在测试客户端服务降级时这里睡眠时间要短，不然openFeign调用接口时超时出错*/
        try { TimeUnit.MILLISECONDS.sleep(timeNumber); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:  "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): "+timeNumber;
//        return "线程池:  "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:  "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    /**
   服务运行失败指定的方法 参数一定要与访问接口的参数一致
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }

    /**********************************服务熔断演示*************************************/
    /**********************************服务熔断演示*************************************/
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value = "60")//失败率
            /**意思是在10秒内有10次请求次数60%的失败率后开启服务熔断  服务熔断开启后，所有请求都不会进行转发，直到慢慢恢复*/
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id<0){//异常后进行服务降级
            throw new RuntimeException("...id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号为"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id不能为负数，请稍后重试  id" +id;
    }
}
