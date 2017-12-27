package com.hjx.controller;

import com.hjx.converter.OrderForm2OrderDTOConverter;
import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.form.OrderForm;
import com.hjx.service.OrderService;
import com.hjx.util.ResultVOUtil;
import com.hjx.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    private OrderService orderService;

    @Autowired
    public BuyerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //创建订单
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数有误，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空，order={}",orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.createOrder(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());


        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        //TODO 不安全，验证身份预留
        OrderDTO orderDTO = orderService.findOne(orderId);

        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        //TODO 不安全，验证身份预留
        OrderDTO queryResult = orderService.findOne(orderId);
        orderService.cancel(queryResult);

        return ResultVOUtil.success();
    }

}
