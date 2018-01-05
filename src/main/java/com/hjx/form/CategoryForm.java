package com.hjx.form;

import lombok.Data;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名称. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
