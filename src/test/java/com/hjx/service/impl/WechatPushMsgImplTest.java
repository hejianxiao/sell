package com.hjx.service.impl;

import com.hjx.dto.OrderDTO;
import com.hjx.service.OrderService;
import com.hjx.service.WechatPushMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatPushMsgImplTest {

    @Autowired
    private WechatPushMsg wechatPushMsg;
    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1514364792458217781");
        wechatPushMsg.orderStatus(orderDTO);

    }

}