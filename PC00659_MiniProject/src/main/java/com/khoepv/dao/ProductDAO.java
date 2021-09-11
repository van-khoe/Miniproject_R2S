package com.khoepv.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.khoepv.entity.Product;


public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	@Query("SELECT o FROM Product o WHERE o.category.idcategories=?1")
	Page<Product> findByCategoryId(String cid, Pageable pageable);

}
