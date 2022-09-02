package com.huanglong.springcloud.service;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.stereotype.Component;

/**
 * 服务器宕机回退处理类 及其方法
 */
@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult paymentSQL(Integer id)
    {
        return new CommonResult(444,"服务降级返回,没有该流水信息",new Payment(id.longValue(), "errorSerial......"));
    }
}
