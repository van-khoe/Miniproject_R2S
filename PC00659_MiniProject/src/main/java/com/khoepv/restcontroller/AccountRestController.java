package com.khoepv.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khoepv.entity.Account;
import com.khoepv.entity.Product;
import com.khoepv.service.AccountService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@GetMapping("{id}")
	public Account getOne(@PathVariable("id") String id) {
		return accountService.findById(id);
	}
	
	@GetMapping("getall")
	public List<Account> getAll() {
		return accountService.findAll();
	}
	
	@PostMapping()
	public Account create(@RequestBody Account account) {
		return accountService.save(account);
	}
	
	@PutMapping("{id}")
	public Account update(@PathVariable("id") String id,@RequestBody Account account) {
		return accountService.update(account);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		accountService.delete(id);
	}
}
