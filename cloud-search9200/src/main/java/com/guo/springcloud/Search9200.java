package com.guo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/3/18 22:46
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Search9200 {
    // 测试
    public static void main(String[] args) {
        SpringApplication.run(Search9200.class, args);
    }
}
