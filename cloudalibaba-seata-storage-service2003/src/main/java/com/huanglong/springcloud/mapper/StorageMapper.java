package com.huanglong.springcloud.mapper;

import com.huanglong.springcloud.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanglong.springcloud.utlis.CommonResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}
