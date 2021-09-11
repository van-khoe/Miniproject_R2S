package com.khoepv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	@RequestMapping({ "/", "/home/index" })
	public String home(Model m) {
		return "redirect:/product/list";
	}
	
	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin(Model m) {
		return "redirect:/assets/admin/index.html";

	}
	@RequestMapping("/home/404")
	public String error(Model m) {
		return "/layout/404";

	}
	
	@RequestMapping("/home/500")
	public String error11(Model m) {
		return "/layout/500";

	}
}
