package com.guo.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.guo.springcloud.entities.CommonResult;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/20 14:57
 * @Description:
 */
public class CustomerBlockHandler {
    // 这里必须是static
    public static CommonResult handlerException(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
