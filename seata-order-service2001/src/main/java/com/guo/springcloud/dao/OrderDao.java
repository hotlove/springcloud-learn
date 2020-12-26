package com.guo.springcloud.dao;

import com.guo.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 15:28
 * @Description:
 */
@Mapper
public interface OrderDao {

    //1 新建订单
    void create(Order order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
