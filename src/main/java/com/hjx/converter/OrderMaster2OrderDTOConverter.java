package com.hjx.converter;

import com.hjx.dataobject.OrderMaster;
import com.hjx.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjx
 * 2017/12/26 0026.
 */
public class OrderMaster2OrderDTOConverter {

    private static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(
                OrderMaster2OrderDTOConverter::convert
        ).collect(Collectors.toList());
    }
}
