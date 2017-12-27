package com.hjx.service.impl;

import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.service.BuyerService;
import com.hjx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    private OrderService orderService;

    @Autowired
    public BuyerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        return null;
    }
}
