package com.huanglong.springcloud.service.impl;

import com.huanglong.springcloud.entity.Account;
import com.huanglong.springcloud.mapper.AccountMapper;
import com.huanglong.springcloud.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
