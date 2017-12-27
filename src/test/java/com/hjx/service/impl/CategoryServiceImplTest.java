package com.hjx.service.impl;

import com.hjx.dataobject.ProductCategory;
import com.hjx.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> resultList = categoryService.findByCategoryTypeIn(Arrays.asList(3,4));
        Assert.assertNotEquals(0,resultList.size());

    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        categoryService.save(productCategory);
    }

}