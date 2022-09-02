package com.huanglong.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanglong.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-24
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
