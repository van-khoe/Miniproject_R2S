package com.khoepv.service;

import java.util.List;

import com.khoepv.entity.Account;

public interface AccountService {

	Account findById(String username);


	List<Account> findAll();

	Account save(Account acc);

	Account update(Account account);

	void delete(String id);
}
