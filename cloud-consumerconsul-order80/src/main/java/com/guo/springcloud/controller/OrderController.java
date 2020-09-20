package com.guo.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/9/20 21:22
 * @Description:
 */
@RestController
public class OrderController {

    public static final String INVOKE_URL = "http://consul-provider-payment"; // 方便使用负载均衡

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }

}
