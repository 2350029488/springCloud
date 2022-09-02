package com.huanglong.springcloud.service.impl;

import com.huanglong.springcloud.entity.Storage;
import com.huanglong.springcloud.mapper.StorageMapper;
import com.huanglong.springcloud.service.IStorageService;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

}
