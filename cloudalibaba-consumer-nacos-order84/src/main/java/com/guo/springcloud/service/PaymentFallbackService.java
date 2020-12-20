package com.guo.springcloud.service;

import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/20 16:18
 * @Description:
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
