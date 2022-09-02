package com.huanglong.springcloud.controller;


import com.huanglong.springcloud.entity.Order;
import com.huanglong.springcloud.service.AccountService;
import com.huanglong.springcloud.service.IOrderService;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/create")
    public CommonResult create(Order order){
        System.out.println(order);
        System.out.println(order.getMoney());
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }

    @GetMapping("/test")
    public String mehtod(){
       return accountService.test();
    }
}
