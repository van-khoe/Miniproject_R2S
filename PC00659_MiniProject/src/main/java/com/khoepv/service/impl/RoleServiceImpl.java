package com.khoepv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoepv.dao.RoleDAO;
import com.khoepv.entity.Role;
import com.khoepv.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO rDAO;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return rDAO.findAll();
	}

//	@Override
//	public Role findById(String string) {
//		// TODO Auto-generated method stub
//		return rDAO.findById(string).get();
//	}

}
