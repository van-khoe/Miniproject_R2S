package com.khoepv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khoepv.dao.OrderDAO;
import com.khoepv.entity.Order;
import com.khoepv.service.OrderService;


@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;

	
	@RequestMapping("/order/checkout")
	public String checkout(Model m) {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model m, HttpServletRequest rq) {
		String username = rq.getRemoteUser();
		m.addAttribute("orders", orderService.finByUsername(username));
		m.addAttribute("countid", orderService.sumByUsername(username));
		try {
			
			m.addAttribute("qtyid", orderService.qtyByUsername(username));
		} catch (Exception e) {
			return "redirect:/home/500";
		}
		
		m.addAttribute("priid", orderService.priByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{idorders}")
	public String detail(Model m, @PathVariable("idorders") Long id, HttpServletRequest rq) {
		String username = rq.getRemoteUser();
		List<Order> o = orderService.finByUsername(username);
		for(Order od : o){
			if(od.getIdorders().equals(id) ) {
				m.addAttribute("order", orderService.finById(id));
				return "order/detail";
			}
		}
		return "redirect:/home/404";
		}

}
