package com.guo.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/9/26 09:47
 * @Description:
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
