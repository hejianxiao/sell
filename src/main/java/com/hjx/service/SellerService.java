package com.hjx.service;

import com.hjx.dataobject.SellerInfo;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
public interface SellerService {

    /** 查询单一卖家信息. */
    SellerInfo findSellerInfoByOpenid(String openid);

}
