package com.huanglong.springcloud;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huanglong.springcloud.entity.Account;
import com.huanglong.springcloud.service.IAccountService;
import org.checkerframework.checker.units.qual.A;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class test {
    @Autowired
    private IAccountService accountService;

    @Test
    public void tetss(){
        LambdaQueryWrapper<Account> queryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Account> eq = queryWrapper.eq(Account::getUserId, 1);
        Account account = accountService.getOne(eq);
        LambdaUpdateWrapper<Account> wrapper=new LambdaUpdateWrapper<>();
        BigDecimal decimal=new BigDecimal(1);
        LambdaUpdateWrapper<Account> updatewapper = wrapper.set(Account::getUsed, account.getUsed()+10)
                .set(Account::getResidue, account.getResidue()-10)
                .eq(Account::getUserId, 1);
        accountService.update(updatewapper);
    }
    }



