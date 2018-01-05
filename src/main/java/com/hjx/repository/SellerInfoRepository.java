package com.hjx.repository;

import com.hjx.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);

}
