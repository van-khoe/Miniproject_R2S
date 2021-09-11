package com.khoepv.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.khoepv.entity.Product;

public interface ProductService {

	Page<Product> findAll(Pageable pageable);

	Product findById(Integer id);

	Page<Product> findByCategoryId(String string, Pageable pageable);

	List<Product> findAll();

}
