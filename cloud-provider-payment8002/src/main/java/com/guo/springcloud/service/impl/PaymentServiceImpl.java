package com.guo.springcloud.service.impl;

import com.guo.springcloud.dao.PaymentDao;
import com.guo.springcloud.entities.Payment;
import com.guo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Date: 2020/8/26 19:52
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
