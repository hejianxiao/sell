package com.hjx.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjx.dataobject.OrderDetail;
import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            Gson gson = new Gson();
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e) {
            log.error("【对象转换】错误，jsonString={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
