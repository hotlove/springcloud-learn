package com.guo.springcloud.controller;


import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/8/26 22:18
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {

//    private final String PAYMENT_URL = "http://localhost:8001";
    private final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; // 方便使用负载均衡

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id , CommonResult.class);
    }
}
