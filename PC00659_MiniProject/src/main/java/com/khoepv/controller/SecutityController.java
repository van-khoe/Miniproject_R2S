package com.khoepv.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khoepv.entity.Account;
import com.khoepv.entity.Authority;
import com.khoepv.entity.Role;
import com.khoepv.service.AccountService;
import com.khoepv.service.AuthorityService;
import com.khoepv.service.RoleService;

@Controller
public class SecutityController {
	@Autowired
	HttpServletRequest rq;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	
	@RequestMapping("/security/login/form")
	public String login(Model m) {
		if(rq.getRemoteUser() == null) {
		Account acc = new Account();
		m.addAttribute("acc", acc);
		return "security/login";
		}
		return "redirect:/home/index";
	}
	@RequestMapping("/security/login/success")
	public String success(Model m) {
		m.addAttribute("message","Đăng nhập thành công!");
		return "forward:/security/login/form";
	}
	@RequestMapping("/security/login/error")
	public String error(Model m) {
		m.addAttribute("message","Sai thông tin đăng nhập!");
		return "forward:/security/login/form";
	}
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model m) {
		m.addAttribute("message","Không có quyền truy xuất!");
		return "forward:/security/login/form";
	}
	@RequestMapping("/security/logoff/success")
	public String logoff(Model m) {
		m.addAttribute("message","Đăng xuất thành công!");
		return "forward:/security/login/form";
	}
	
	

}
