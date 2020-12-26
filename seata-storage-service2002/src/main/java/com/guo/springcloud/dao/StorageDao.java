package com.guo.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:30
 * @Description:
 */
@Mapper
public interface StorageDao {
    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
