package com.example.demo.util.result;

import lombok.Data;

/**
 * @author longzhonghua
 * @data 2019/01/14 15:30
 */
@Data
public class Result<T> {

    // 错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体内容
    private T data;


}
