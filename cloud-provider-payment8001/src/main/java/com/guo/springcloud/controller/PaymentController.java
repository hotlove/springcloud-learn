package com.guo.springcloud.controller;

import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import com.guo.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @Date: 2020/8/26 19:55
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
//@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    public CommonResult create(Payment payment) {

    }
}
