package com.hjx.dataobject;

import com.hjx.enums.OrderStatusEnum;
import com.hjx.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    
    /** 订单id. */
    @Id
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态，默认0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    private Date createTime;
    
    /** 更新时间. */
    private Date updateTime;

}
