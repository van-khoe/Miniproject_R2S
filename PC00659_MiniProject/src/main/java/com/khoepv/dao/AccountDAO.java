package com.khoepv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.khoepv.entity.Account;



public interface AccountDAO extends JpaRepository<Account, String>{
}
