package com.huanglong.springcloud.controller;


import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.service.IPaymentService;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    /*8001 8002服务提供者注册到eureka中的一些基础信息  通过服务发现把信息暴露出来*/
    @Autowired      //注意导包
    private DiscoveryClient discoveryClient;


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



    /*** 测试eureka中注册的服务有哪些*/
    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
       //获得服务清单列表
        for (String service:services){
            log.info(".......service:{}",service);
            /*输出内容.......service:cloud-payment-service
                   : .......service:cloud-order-service */
        }
        //或者通过微服务名称进一步获取信息 只有CLOUD-PAYMENT-SERVICE的信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+"\t"
 +instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
/**输出内容 * CLOUD-PAYMENT-SERVICE	192.168.17.1	8002	http://192.168.17.1:8002
* : CLOUD-PAYMENT-SERVICE	192.168.17.1	8001	http://192.168.17.1:8001*/
        }
        return this.discoveryClient;
    }


    /*测试自定义负载均衡算法*/
    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    /*测试openFeign连接超时*/
    @GetMapping("/runTimeout")
    public String runTimeout(){
        try {
//            沉睡3秒再连接
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
