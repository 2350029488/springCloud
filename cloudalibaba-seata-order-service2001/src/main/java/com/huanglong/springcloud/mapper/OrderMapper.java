package com.huanglong.springcloud.mapper;

import com.huanglong.springcloud.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    //1.新建订单
    void create(Order order);

    //1. 下单成功->库存扣减成功——>账户扣减成功 == 需要修改订单状态 从0改为1

    void  update(@Param("userId") Long userId,@Param("status")Integer status);


}
