package com.huanglong.springcloud.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huanglong.springcloud.entity.Storage;
import com.huanglong.springcloud.service.IStorageService;
import com.huanglong.springcloud.utlis.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private IStorageService storageService;
    @PostMapping("/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId, @RequestParam("count")Integer count){
        LambdaQueryWrapper<Storage> queryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Storage> eq = queryWrapper.eq(Storage::getProductId, productId);
        Storage storage = storageService.getOne(eq);
        LambdaUpdateWrapper<Storage> wrapper=new LambdaUpdateWrapper<>();
        LambdaUpdateWrapper<Storage> updatewapper = wrapper.set(Storage::getUsed, storage.getUsed()+count)
                .set(Storage::getResidue,storage.getResidue()-count)
                .eq(Storage::getProductId, productId);
        System.out.println("------->storage-service中扣减库存开始");
        storageService.update(updatewapper);
        System.out.println("------->storage-service中扣减库存开始");
        return new CommonResult(200,"账户扣款成功");

    }
}
