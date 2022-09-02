package com.huanglong.springcloud;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.huanglong.springcloud.entity.Order;
import com.huanglong.springcloud.service.IOrderService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class MybtisPlusTest {
    @Autowired
    private IOrderService orderService;

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/seata_order?serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("黄隆") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\SpringCloud\\springCloudStudy\\cloudhuanglong\\cloudalibaba-seata-order-service2001\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.huanglong.springcloud") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\SpringCloud\\springCloudStudy\\cloudhuanglong\\cloudalibaba-seata-order-service2001\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_order")// 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    public void tets(){
        List<Order> list = orderService.list();
        list.forEach(System.out::println);
    }
}
