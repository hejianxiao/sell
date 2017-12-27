package com.hjx.service;

import com.hjx.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
