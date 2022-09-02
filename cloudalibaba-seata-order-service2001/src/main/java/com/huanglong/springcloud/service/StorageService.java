package com.huanglong.springcloud.service;

import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存接口
 */
@Component
@FeignClient(value = "seata-storage-service")  //调用接口 连接库存微服务
public interface StorageService {

    //库存减数量 通过产品id productId获取这条信息，对这条信息的count进行减少
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId")Integer productId,@RequestParam("count")Integer count);

}
