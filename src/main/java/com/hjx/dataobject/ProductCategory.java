package com.hjx.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    /** 类目id. */
    @Id
    @GeneratedValue
    private Integer categoryId;
    
    /** 类目名称. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
