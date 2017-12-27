package com.hjx.repository;

import com.hjx.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}
