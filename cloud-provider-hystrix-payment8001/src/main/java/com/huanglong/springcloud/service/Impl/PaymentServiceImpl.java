package com.huanglong.springcloud.service.Impl;

import com.huanglong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
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
        try { TimeUnit.MILLISECONDS.sleep(timeNumber*1000); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:  "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): "+timeNumber;
//        return "线程池:  "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:  "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    /**
   服务运行失败指定的方法 参数一定要与访问接口的参数一致
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }
}
