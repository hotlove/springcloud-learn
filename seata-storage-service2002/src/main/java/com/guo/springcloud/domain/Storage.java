package com.guo.springcloud.domain;

import lombok.Data;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:31
 * @Description:
 */
@Data
public class Storage {
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
