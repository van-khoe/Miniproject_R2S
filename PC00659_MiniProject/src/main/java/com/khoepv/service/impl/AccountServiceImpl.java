package com.khoepv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoepv.dao.AccountDAO;
import com.khoepv.entity.Account;
import com.khoepv.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO accDAO;

	@Override
	public Account findById(String username) {
		
		return accDAO.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accDAO.findAll();
	}

	@Override
	public Account save(Account acc) {
		// TODO Auto-generated method stub
		return accDAO.save(acc);
	}


	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return accDAO.save(account);
	}

	@Override
	public void delete(String id) {
		accDAO.deleteById(id);
		
	}
}
