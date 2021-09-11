package com.khoepv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoepv.dao.OrderDAO;
import com.khoepv.dao.OrderDetailDAO;
import com.khoepv.entity.Order;
import com.khoepv.entity.OrderDetail;
import com.khoepv.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO oDAO;
	
	@Autowired
	OrderDetailDAO odDAO;


	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		oDAO.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		odDAO.saveAll(details);
		return order;
	}

	@Override
	public Order finById(Long id) {
		return oDAO.findById(id).get();
		
	}

	@Override
	public List<Order> finByUsername(String username) {
		// TODO Auto-generated method stub
		return oDAO.findByUsername(username);
	}

	@Override
	public int sumByUsername(String username) {
		// TODO Auto-generated method stub
		return oDAO.sumByUsername(username);
	}

	@Override
	public int qtyByUsername(String username) {
		// TODO Auto-generated method stub
		return oDAO.sumqtyByUsername(username);
	}

	@Override
	public Double priByUsername(String username) {
		// TODO Auto-generated method stub
		return oDAO.sumpriByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return oDAO.findAll();
	}

}
