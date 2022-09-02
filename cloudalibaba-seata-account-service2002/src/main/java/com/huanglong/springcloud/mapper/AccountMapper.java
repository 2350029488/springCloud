package com.huanglong.springcloud.mapper;

import com.huanglong.springcloud.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
