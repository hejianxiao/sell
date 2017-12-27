package com.hjx.repository;

import com.hjx.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
