package com.hjx.controller;

import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hjx
 * 2018/1/4 0004.
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    private OrderService orderService;

    @Autowired
    public SellerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 订单列表
     * @param page 第几页，从第一页开始
     * @param size 一页多少条数据
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(page - 1,size,sort);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        model.addAttribute("orderDTOPage",orderDTOPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "order/list";
    }

    /**
     * 取消订单
     * @param orderId 订单id
     */
    @RequestMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId,
                         Model model) {
        model.addAttribute("url", "list");
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常={}",e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "common/error";
        }
        return "common/success";
    }

    /**
     * 订单详情
     * @param orderId 订单id
     */
    @RequestMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,
                         Model model) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
            model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端订单详情】发生异常={}",e.getMessage());
            model.addAttribute("url", "list");
            model.addAttribute("msg", e.getMessage());
            return "common/error";
        }

        model.addAttribute("orderDTO", orderDTO);
        return "order/detail";
    }

    /**
     * 完成订单
     * @param orderId 订单id
     */
    @RequestMapping("/finish")
    public String finish(@RequestParam("orderId") String orderId,
                         Model model) {
        model.addAttribute("url", "list");
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常={}",e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "common/error";
        }
        return "common/success";
    }


}
