package com.hjx.service;

import com.hjx.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public interface OrderService {
    /** 创建订单. */
    OrderDTO createOrder(OrderDTO orderDTO);

    /** 查询单个订单. */
    OrderDTO findOne(String orderId);

    /** 查询订单列表. */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);


    /** 查询订单列表. */
    Page<OrderDTO> findList(Pageable pageable);
}
