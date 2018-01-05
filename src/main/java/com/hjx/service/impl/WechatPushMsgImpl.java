package com.hjx.service.impl;

import com.hjx.dto.OrderDTO;
import com.hjx.service.WechatPushMsg;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@Service
@Slf4j
public class WechatPushMsgImpl implements WechatPushMsg{

    private WxMpService wxMpService;

    @Autowired
    public WechatPushMsgImpl(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        //测试账号openid = oMDAY016uexcwSsRzJike_I9BiP4
        //测试模板id = diTrypX9Q_us2BQE890gpvBEU8FR22pIGe4UA8F10iE

        List<WxMpTemplateData> data = Arrays.asList(
            new WxMpTemplateData("first","请记得收货"),
            new WxMpTemplateData("keyword1", "微信点餐"),
            new WxMpTemplateData("keyword2", "17691224441"),
            new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
            new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()),
            new WxMpTemplateData("keyword5", "¥"+orderDTO.getOrderAmount()),
            new WxMpTemplateData("remark","欢迎再次光临!")
        );
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("diTrypX9Q_us2BQE890gpvBEU8FR22pIGe4UA8F10iE");
        templateMessage.setData(data);
        templateMessage.setToUser("oMDAY016uexcwSsRzJike_I9BiP4");
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败,{}",e);

        }

    }
}
