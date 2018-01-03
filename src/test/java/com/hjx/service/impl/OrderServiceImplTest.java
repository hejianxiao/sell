package com.hjx.service.impl;

import com.hjx.dataobject.OrderDetail;
import com.hjx.dto.OrderDTO;
import com.hjx.enums.OrderStatusEnum;
import com.hjx.enums.PayStatusEnum;
import com.hjx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "oTgZpwQs3APeRwtNramWQzRA1cfo";

    private final String ORDERID = "1514252911437928739";

    @Autowired
    private OrderService orderService;


    @Test
    public void createOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("王栋个小2B");
        orderDTO.setBuyerPhone("15929941019");
        orderDTO.setBuyerAddress("长安县北里王");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(1);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne(ORDERID);
        log.info("【查询单一订单】result={}",result);
        Assert.assertEquals(ORDERID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

}