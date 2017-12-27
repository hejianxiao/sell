package com.hjx.repository;

import com.hjx.dataobject.OrderDetail;
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
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("22");
        orderDetail.setOrderId("11111");
        orderDetail.setProductIcon("http://asb.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("麻辣米线");
        orderDetail.setProductPrice(new BigDecimal(5));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> list = repository.findByOrderId("11111");
        Assert.assertNotEquals(0,list.size());
    }

}