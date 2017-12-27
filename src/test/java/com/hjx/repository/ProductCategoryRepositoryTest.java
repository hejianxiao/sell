package com.hjx.repository;

import com.hjx.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱",4);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> findList = Arrays.asList(3,4);
        List<ProductCategory> resultList = productCategoryRepository.findByCategoryTypeIn(findList);
        Assert.assertNotEquals(0,resultList.size());
    }

}