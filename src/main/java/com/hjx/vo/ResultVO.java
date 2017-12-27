package com.hjx.vo;

import lombok.Data;

/**
 * http请求返回最外层
 * Created by hjx
 * 2017/12/25 0025.
 */
@Data
public class ResultVO<T> {
    
    /** 状态码. */
    private Integer code;
    
    /** 提示信息. */
    private String msg;
    
    /** 具体内容. */
    private T data;
}
