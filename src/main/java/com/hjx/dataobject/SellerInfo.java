package com.hjx.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息
 * Created by hjx
 * 2018/1/5 0005.
 */
@Data
@Entity
public class SellerInfo {
    
    /** 主键. */
    @Id
    private String sellerId;
    
    /** 用户名. */
    private String username;

    /** 密码. */
    private String password;
    
    /** 微信openid. */
    private String openid;

    
}
