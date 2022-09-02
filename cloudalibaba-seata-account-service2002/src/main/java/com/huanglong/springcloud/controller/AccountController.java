package com.huanglong.springcloud.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huanglong.springcloud.entity.Account;
import com.huanglong.springcloud.service.IAccountService;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @PostMapping("/decrease")
   public CommonResult decrease(@RequestParam("userId")Long userId, @RequestParam("money") BigDecimal money){

        LambdaQueryWrapper<Account> queryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Account> eq = queryWrapper.eq(Account::getUserId, userId);
        Account account = accountService.getOne(eq);
        LambdaUpdateWrapper<Account> wrapper=new LambdaUpdateWrapper<>();
        LambdaUpdateWrapper<Account> updatewapper = wrapper.set(Account::getUsed, account.getUsed().add(money))
                .set(Account::getResidue, account.getResidue().subtract(money))
                .eq(Account::getUserId, userId);
        System.out.println("------->account-service中扣减账户余额开始");
        //模拟超时异常
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountService.update(updatewapper);
        System.out.println("------->account-service中扣减账户余额结束");
        return new CommonResult(200,"账户扣款成功");

    }
    @GetMapping("/test")
    public String test(){
        return "正确";
    }
}
