package com.hjx.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Entity
@Data
public class ProductInfo {
    
    /** 商品id. */
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;



}
