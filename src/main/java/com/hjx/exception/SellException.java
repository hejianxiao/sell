package com.hjx.exception;

import com.hjx.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(ResultEnum resultEnum,String msg) {
        super(msg);
        this.code = resultEnum.getCode();
    }

}
