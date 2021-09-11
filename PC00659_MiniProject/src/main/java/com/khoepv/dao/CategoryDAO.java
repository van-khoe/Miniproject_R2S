package com.khoepv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khoepv.entity.Category;



public interface CategoryDAO extends JpaRepository<Category, String>{

}
