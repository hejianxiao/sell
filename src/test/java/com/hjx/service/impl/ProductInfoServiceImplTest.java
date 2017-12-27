package com.hjx.service.impl;

import com.hjx.dataobject.ProductInfo;
import com.hjx.enums.ProductStatusEnum;
import com.hjx.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;


    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productInfoService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> page = productInfoService.findAll(request);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("麻辣米线");
        productInfo.setProductPrice(new BigDecimal(5));
        productInfo.setProductStock(15);
        productInfo.setProductDescription("好吃的麻辣米线");
        productInfo.setProductIcon("http://bbb.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(3);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

}