package com.huanglong.springcloud.controller;


import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.service.IPaymentService;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-24
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("创造一条数据:{}",payment);
        int i = paymentService.create(payment);
        if (i!=0){
            return new CommonResult(200,"插入成功,使用的端口号是:"+serverPort,i);
        }else {
            return new CommonResult(200,"插入失败",i);
        }
    }

    @GetMapping ("/get/{payId}")
    public CommonResult getPaymentById(@PathVariable("payId")Long payId){
        Payment paymentById = paymentService.getPaymentById(payId);
        if (paymentById!=null){
            return new CommonResult(200,"查询成功,使用的端口号是:"+serverPort,paymentById);
        }else {
            return new CommonResult(200,"查询失败",paymentById);
        }
    }

}
