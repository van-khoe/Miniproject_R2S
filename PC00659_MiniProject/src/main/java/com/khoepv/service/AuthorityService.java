package com.khoepv.service;

import java.util.List;

import com.khoepv.entity.Authority;
import com.khoepv.entity.Role;


public interface AuthorityService {

	public List<Authority> findAll();

	public Authority create(Authority auth);

	public void delete(Integer id);

	public List<Role> findRoleByUsername(String username);

	public void delete2(String id);


}
