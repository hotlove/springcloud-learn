package com.guo.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/8/19 21:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    // 404 not_found
    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

//    public CommonResult(Integer code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }
}
