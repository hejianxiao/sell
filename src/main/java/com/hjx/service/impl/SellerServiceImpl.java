package com.hjx.service.impl;

import com.hjx.dataobject.SellerInfo;
import com.hjx.repository.SellerInfoRepository;
import com.hjx.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@Service
public class SellerServiceImpl implements SellerService{

    private SellerInfoRepository sellerInfoRepository;

    @Autowired
    public SellerServiceImpl(SellerInfoRepository sellerInfoRepository) {
        this.sellerInfoRepository = sellerInfoRepository;
    }

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
