package com.guo.springcloud.service;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:35
 * @Description:
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
