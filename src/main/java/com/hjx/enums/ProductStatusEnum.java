package com.hjx.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by hjx
 * 2017/12/25 0025.
 */
@Getter
public enum ProductStatusEnum implements CodeEnum<Integer>{

    UP(0,"在架"),DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
