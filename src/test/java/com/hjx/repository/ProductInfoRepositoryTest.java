package com.hjx.repository;

import com.hjx.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("麻辣米线");
        productInfo.setProductPrice(new BigDecimal(5.00));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("好吃的米线");
        productInfo.setProductIcon("http://aaaaa.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        repository.save(productInfo);
    }


    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }

}