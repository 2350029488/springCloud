package com.huanglong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
@Slf4j
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    private String testA(){
        log.info("testA 测试RT");
        try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
        return "..............testA";
    }
    public static int i=1;
    @GetMapping("/testB")
    private String testB(){
//        try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
        i++;
        System.out.println(Thread.currentThread().getName()+"\t"+"sss"+i);
        return "..............testB";
    }

    @GetMapping("/testD")
    public String testD(){
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
         int i=10/0;

        log.info("testD 测试RT");
        return "........testD";
    }
    @GetMapping("/testE")
    public String testE(){
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        int i=10/0;
        log.info("testD 测试异常数");
        return "........测试异常数";
    }

    /**
         服务限流
     blockHandler = "deal_testHotkey" 意思是当前链接地址违背了我们在sentinel的设置的热点规则，那么通过blockHandler
     返回处理  类似hystrix的兜底方法
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "HotKey",blockHandler = "deal_testHotkey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2  ){
        return "-------testHoKey";
    }
    /*兜底方法  注意参数要有BlockException  */
    public String deal_testHotkey(String p1, String p2, BlockException exception){
        return "超出sentinel设置的热点规则";
    }


}
