package com.huanglong.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.utlis.CommonResult;

/**
 * 统一处理类
 */
public class TestBloakHandler {
    //本案例是blockHandler
    public static CommonResult<Payment> bloakHandler(Integer id, BlockException blockException){
        Payment payment=new Payment(id.longValue(),"null");
        return new CommonResult<>(445,"sentinel限流，异常为："+blockException.getMessage(),payment);
    }
}
