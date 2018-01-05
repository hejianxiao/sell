package com.hjx.service.impl;

import com.hjx.dataobject.ProductCategory;
import com.hjx.repository.ProductCategoryRepository;
import com.hjx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    private ProductCategoryRepository repository;

    @Autowired
    public CategoryServiceImpl( ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
