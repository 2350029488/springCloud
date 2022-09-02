package com.huanglong.springcloud.handler;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.utlis.CommonResult;

public class TestFallback {
    public static CommonResult<Payment> handlerFallback(Integer id, Throwable e){
        Payment payment=new Payment(id.longValue(),"null");
        return new CommonResult<>(444,"兜底异常内容"+e.getMessage(),payment);

    }
}
