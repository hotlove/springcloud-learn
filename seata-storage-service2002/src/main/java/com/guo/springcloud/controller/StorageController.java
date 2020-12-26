package com.guo.springcloud.controller;

import com.guo.springcloud.domain.CommonResult;
import com.guo.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:36
 * @Description:
 */
@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
