package com.hjx.dataobject.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by hjx
 * 2018/1/8 0008.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void inserByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();

        map.put("category_name","饮品");
        map.put("category_type",5);
        int result = mapper.inserByMap(map);
        Assert.assertEquals(1,result);

    }

}