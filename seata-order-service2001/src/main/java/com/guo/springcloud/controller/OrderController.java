package com.guo.springcloud.controller;

import com.guo.springcloud.domain.CommonResult;
import com.guo.springcloud.domain.Order;
import com.guo.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:07
 * @Description:
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
