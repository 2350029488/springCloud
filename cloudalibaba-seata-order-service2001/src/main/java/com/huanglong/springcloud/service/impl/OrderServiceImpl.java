package com.huanglong.springcloud.service.impl;

import com.huanglong.springcloud.entity.Order;
import com.huanglong.springcloud.mapper.OrderMapper;
import com.huanglong.springcloud.service.AccountService;
import com.huanglong.springcloud.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanglong.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类  订单服务
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
   @Autowired
   private OrderMapper orderMapper;
   @Autowired
   private StorageService storageService;
   @Autowired
   private AccountService accountService;
    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     */

    @Override
    @GlobalTransactional(name = "shuibianjiao",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("......开始创建订单");
        orderMapper.create(order);
        log.info(".........订单微服务调用库存，做扣减数量开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info(".........订单微服务调用库存，做扣减数量结束");

        log.info(".........订单微服务调用账户，做扣减金钱开始");

        accountService.decrease(order.getUserId().longValue(),order.getMoney());
        log.info(".........订单微服务调用账户，做扣减金钱结束");
        log.info("...........修改订单状态");
        orderMapper.update(order.getUserId(),0);

        log.info("......下订单结束");
    }

    @Override
    public void update(Long userId, Integer status) {
      orderMapper.update(userId,status);
    }
}
