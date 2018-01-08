package com.hjx.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 * Created by hjx
 * 2017/12/25 0025.
 */
@Data
public class ProductVO implements Serializable{

    private static final long serialVersionUID = -3123447505311362272L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
