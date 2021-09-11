package com.khoepv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khoepv.entity.Product;
import com.khoepv.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/list")
	public String list(Model m, @RequestParam("cid") Optional<String> cid,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		if(cid.isEmpty()) {
		Page<Product> list1 = productService.findAll(pageable);
		m.addAttribute("page", list1);
		List<Product> list = list1.getContent();
		m.addAttribute("items", list);
		}
		else {
			Page<Product> list1 = productService.findByCategoryId(cid.get(), pageable);
			m.addAttribute("page", list1);
			List<Product> list = list1.getContent();
			m.addAttribute("items", list);
		}
		
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{idproducts}")
	public String detail(Model m, @PathVariable("idproducts") Integer id) {
		Product item = productService.findById(id);
		m.addAttribute("item", item);
		return "product/detail";
	}
}
