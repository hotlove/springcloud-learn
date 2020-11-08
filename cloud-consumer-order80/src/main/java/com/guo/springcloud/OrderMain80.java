package com.guo.springcloud;

import com.guo.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/8/26 22:08
 * @Description:
 */
@SpringBootApplication
// 从Spring Cloud Edgware开始，@EnableEurekaClient 或 @EnableDiscoveryClient 是可省略的。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMNET-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
