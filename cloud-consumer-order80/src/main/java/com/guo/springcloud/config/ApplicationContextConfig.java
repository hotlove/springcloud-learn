package com.guo.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/8/26 22:24
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    // 注释掉是为了证明使用自己写得ribbon
//    @LoadBalanced // 使resttemplate具有负载均衡能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
