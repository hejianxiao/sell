package com.hjx.repository;

import com.hjx.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("222222");
        orderMaster.setBuyerName("田浩然");
        orderMaster.setBuyerPhone("15929941019");
        orderMaster.setBuyerAddress("长安区北里王浩南哥");
        orderMaster.setBuyerOpenid("2222");
        orderMaster.setOrderAmount(new BigDecimal(300));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0,5);
        Page<OrderMaster> page = repository.findByBuyerOpenid("2222",request);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

}