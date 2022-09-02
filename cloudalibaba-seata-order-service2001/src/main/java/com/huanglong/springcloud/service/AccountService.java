package com.huanglong.springcloud.service;

import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 减账户(余额)接口
 */
@Component
@FeignClient(value = "seata-account-service")  //调用接口 连接减账户微服务
public interface AccountService {
    //通过用户id执行扣钱工作 两个参数  userid  money
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId")Integer userId, @RequestParam("money") Integer money);

  @GetMapping("/account/test")
    public String test();

}
