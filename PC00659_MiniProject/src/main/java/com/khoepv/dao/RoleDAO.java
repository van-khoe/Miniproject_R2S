package com.khoepv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khoepv.entity.Role;


public interface RoleDAO extends JpaRepository<Role, String>{

}
