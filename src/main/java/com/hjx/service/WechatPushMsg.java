package com.hjx.service;

import com.hjx.dto.OrderDTO;

/**
 * 微信公众号推送消息
 * Created by hjx
 * 2018/1/5 0005.
 */
public interface WechatPushMsg {
    void orderStatus(OrderDTO orderDTO);
}
