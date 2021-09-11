package com.khoepv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {

	@RequestMapping("/cart/view")
	public String view(Model m) {
		return "cart/view";
	}

}
