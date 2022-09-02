package com.huanglong.springcloud.service;

import com.huanglong.springcloud.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
public interface IOrderService extends IService<Order> {
    void create(Order order);

    //1. 下单成功->库存扣减成功——>账户扣减成功 == 需要修改订单状态 从0改为1

    void  update( Long userId,Integer status);
}
