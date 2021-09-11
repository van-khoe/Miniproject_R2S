package com.khoepv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khoepv.entity.OrderDetail;



public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}
