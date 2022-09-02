package com.huanglong.springcloud;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huanglong.springcloud.entity.Storage;
import com.huanglong.springcloud.service.IStorageService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    IStorageService storageService;
    @Test
    public void sss(){
        LambdaQueryWrapper<Storage> queryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Storage> eq = queryWrapper.eq(Storage::getProductId, 1);
        Storage storage = storageService.getOne(eq);
        LambdaUpdateWrapper<Storage> wrapper=new LambdaUpdateWrapper<>();
        LambdaUpdateWrapper<Storage> updatewapper = wrapper.set(Storage::getUsed, storage.getUsed()+20)
                .set(Storage::getResidue,storage.getResidue()-20)
                .eq(Storage::getProductId, 1);
        storageService.update(updatewapper);
    }
}
