package com.hjx.repository;

import com.hjx.dataobject.SellerInfo;
import com.hjx.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save() throws Exception {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("123");
        sellerInfo.setPassword("admin");
        sellerInfo.setUsername("admin");
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = repository.findByOpenid("123");
        Assert.assertNotNull(sellerInfo);
    }

}