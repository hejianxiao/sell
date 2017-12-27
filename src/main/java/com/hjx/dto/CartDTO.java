package com.hjx.dto;

import lombok.Data;

/**
 * 购物车
 * Created by hjx
 * 2017/12/26 0026.
 */
@Data
public class CartDTO {
    /** 商品id. */
    private String productId;

    /** 购买数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
