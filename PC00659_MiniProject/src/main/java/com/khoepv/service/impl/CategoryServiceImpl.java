package com.khoepv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoepv.dao.CategoryDAO;
import com.khoepv.entity.Category;
import com.khoepv.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDAO cateDAO;

	@Override
	public List<Category> findAll() {
		return cateDAO.findAll();
	}

}
