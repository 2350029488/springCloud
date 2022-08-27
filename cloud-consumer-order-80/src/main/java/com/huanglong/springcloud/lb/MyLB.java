package com.huanglong.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component//放入组件中
public class MyLB implements LoadBalaner{
    //原子整数 从0开始
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    /*Increment增量 表示第几次访问*/
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();//获取当前值
//如果大于最大数值则为0 否则为1  主要是防止访问次数超出最大数值
            next=current>=Integer.MAX_VALUE?0:current+1;
//自旋锁 如果这两个数不相等返回true ，取反则为flase,跳出循环
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("next:"+next);
        return next;
    }


    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //访问次数%服务器个数
        int index = getAndIncrement()%serviceInstances.size();
        //返回下表为index的服务器
        return serviceInstances.get(index);
    }
}
