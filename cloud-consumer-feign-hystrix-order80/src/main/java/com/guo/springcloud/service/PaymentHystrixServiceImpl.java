package com.guo.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/27 22:45
 * @Description:
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentHystrixService----paymentInfoOk--";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "PaymentHystrixService----paymentInfoTimeout--";
    }
}
