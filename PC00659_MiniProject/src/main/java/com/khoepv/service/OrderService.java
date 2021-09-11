package com.khoepv.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.khoepv.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order finById(Long id);

	List<Order> finByUsername(String username);

	int sumByUsername(String username);

	int qtyByUsername(String username);

	Double priByUsername(String username);

	List<Order> findAll();

}
