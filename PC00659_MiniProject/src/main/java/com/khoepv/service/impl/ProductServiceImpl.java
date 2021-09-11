package com.khoepv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.khoepv.dao.ProductDAO;
import com.khoepv.entity.Product;
import com.khoepv.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO proDAO;

	@Override
	public Page<Product> findAll(Pageable pageable) {
		
		return proDAO.findAll(pageable);
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return proDAO.findById(id).get();
	}

	@Override
	public Page<Product> findByCategoryId(String cid,Pageable pageable) {
		// TODO Auto-generated method stub
		return proDAO.findByCategoryId(cid, pageable);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return proDAO.findAll();
	}


}
