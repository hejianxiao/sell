package com.hjx.controller;

import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.service.OrderService;
import com.hjx.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hjx
 * 2017/12/27 0027.
 */
@Controller
//@RequestMapping("/pay")
@Slf4j
public class PayController {

    private OrderService orderService;
    private PayService payService;

    @Autowired
    public PayController(OrderService orderService,PayService payService) {
        this.orderService = orderService;
        this.payService = payService;
    }

    @GetMapping("/pay")
    public String pay(@RequestParam("openid") String openid,Model model){
        log.info("openid={}",openid);
        //查询订单
        OrderDTO orderDTO = orderService.findOne("1515035437181988194");
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);

        model.addAttribute("payResponse",payResponse);
        //发起支付
        return "pay/create";
    }

    @GetMapping("/create")
    public String create(@RequestParam("orderId") String orderId,
                         @RequestParam("returnUrl") String returnUrl,
                         Model model) {

        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);

        model.addAttribute("payResponse",payResponse);
        //发起支付
        return "pay/create";
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }


}
