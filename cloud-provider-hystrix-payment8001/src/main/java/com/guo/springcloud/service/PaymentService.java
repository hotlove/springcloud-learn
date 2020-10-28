package com.guo.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoOk,id:" + id + "\t" + "O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            // fallbackMethod 指定降级的方法
            // 这里是服务提供端
            // 这里可以理解为等待业务处理3s 若是3s没处理完则进行降级
            // 或者业务报错也会进行降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="4000")
    })
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
//            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOut,id:" + id + "\t" + "O(∩_∩)O"+"耗时"+timeNumber+"s";
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOut,id:" + id + "\t" + "/(ㄒoㄒ)/~~";
    }
}
