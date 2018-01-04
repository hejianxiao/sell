package com.hjx.enums;

import lombok.Getter;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Getter
public enum PayStatusEnum implements CodeEnum<Integer>{
    WAIT(0,"等待支付"),SUCCESS(1,"支付成功");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
