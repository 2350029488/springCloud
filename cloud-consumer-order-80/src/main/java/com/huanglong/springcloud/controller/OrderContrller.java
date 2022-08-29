package com.huanglong.springcloud.controller;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.lb.LoadBalaner;
import com.huanglong.springcloud.utlis.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 消费者 通过RestTemplate 向8001端口发送数据请求
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderContrller {
// public static final String PAYMENT_URL="http://localhost:8001";
    /**  修改访问端口为注册中心中注册的地址 CLOUD-PAYMENT-SERVICE为8001和 8002的别名  同时开启负载均衡 */
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;
/*源代码参数    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {*/

    @Autowired //自定义负载均衡算法
    private LoadBalaner loadBalaner;
    @Autowired//注意导包
    private DiscoveryClient discoveryClient;

     @GetMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
         log.info("跨工程调用");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{payId}")
    public CommonResult<Payment> getPayment(@PathVariable("payId")Long payId){
         return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+payId,CommonResult.class);

    }

    /** ribbon
     * restTemplate.getForObject 返回对象为响应体中数据转化成的对象，
     * 基本上可以理解为Json
     * restTemplate.getForEntity 返回对象为ResponseEntity对象，
     * 包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
     * post是一样的
     */
    @PostMapping("/payment/postForEntity")
    public CommonResult<Payment> create2(@RequestBody Payment payment){

        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){//通过状态码判断是否成功
        /*可以获取响应头、响应状态码、响应体等 */
        log.info(entity.getStatusCode()+"\t"+entity.getHeaders());
          return   entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/payment/getForEntity/{payId}")
    public CommonResult<Payment> getPayment2(@PathVariable("payId")Long payId){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + payId, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){//通过状态码判断是否成功
            /*可以获取响应头、响应状态码、响应体等 */
            log.info(forEntity.getStatusCode()+"\t"+forEntity.getHeaders());
            return   forEntity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    /**测试自定义负载均衡算法  注意这里使用了自定义的，那么所有请求方法都要这么写 上面的这两个请求也是*/
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
//        获取这个服务名称下面的服务器集合
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null||instances.size()<=0){
            return null;
        }else {
            //调用自定义的loadBalaner.instances 返回轮询过后的服务器
            ServiceInstance serviceInstance = loadBalaner.instances(instances);
            URI uri = serviceInstance.getUri();//获取url
            // 发送给8001 或者8002后返回数据
            return restTemplate.getForObject(uri+"/payment/lb",String.class);

        }

    }
//    @GetMapping("/payment/lb")
//    public String getPaymentLB(){
//       return restTemplate.getForObject(PAYMENT_URL + "/payment/lb/",String.class);
//
//    }
}
