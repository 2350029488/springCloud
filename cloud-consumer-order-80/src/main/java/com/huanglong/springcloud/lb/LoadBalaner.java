package com.huanglong.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 手写负载均衡器
 */
public interface LoadBalaner {
    /* 参数为服务实例集合 */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
