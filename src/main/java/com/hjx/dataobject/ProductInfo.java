package com.hjx.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hjx.enums.ProductStatusEnum;
import com.hjx.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Entity
@Data
@DynamicUpdate
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
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

    /** 创建时间. */
    private Date createTime;

    /** 修改时间. */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

}
