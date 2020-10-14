package com.guo.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/14 21:37
 * @Description:
 */
@Service
public class PaymentService {

    /**
     * 正常访问肯定ok得方法·
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK" + id + "\t" + "O(∩_∩)O";
    }
}
