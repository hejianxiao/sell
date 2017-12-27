package com.hjx.service;

import com.hjx.dto.OrderDTO;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
public interface BuyerService {

    /** 查询一个订单. */
    OrderDTO findOrderOne(String openid,String orderId);

    /** 取消订单. */
    OrderDTO cancelOrder(String openid,String orderId);
}
