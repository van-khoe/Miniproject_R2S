package com.khoepv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.khoepv.dao.AccountDAO;
import com.khoepv.dao.AuthorityDAO;
import com.khoepv.entity.Account;
import com.khoepv.entity.Authority;
import com.khoepv.entity.Role;
import com.khoepv.service.AuthorityService;



@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO authoDAO;
	@Autowired
	AccountDAO accDAO;
	
	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authoDAO.findAll();
	}


	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return authoDAO.save(auth);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		authoDAO.deleteById(id);
	}

	@Override
	public List<Role> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return authoDAO.findRoleByUsername(username);
	}

	@Override
	public void delete2(String id) {
		authoDAO.deletepri(id);
		
	}


}
