package com.huanglong.springcloud;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
@SpringBootTest


public class MybatisTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/seata_storage?serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("黄隆") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\SpringCloud\\springCloudStudy\\cloudhuanglong\\cloudalibaba-seata-storage-service2003\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.huanglong.springcloud") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\SpringCloud\\springCloudStudy\\cloudhuanglong\\cloudalibaba-seata-storage-service2003\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_storage")// 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
