package com.huanglong.springcloud.controller;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者 通过RestTemplate 向8001端口发送数据请求
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderContrller {
 public static final String PAYMENT_URL="http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;
/*源代码参数    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {*/
     @GetMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
         log.info("跨工程调用");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{payId}")
    public CommonResult<Payment> getPayment(@PathVariable("payId")Long payId){
         return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+payId,CommonResult.class);
    }
}
