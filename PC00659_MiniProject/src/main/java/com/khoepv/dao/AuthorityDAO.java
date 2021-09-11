package com.khoepv.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khoepv.entity.Account;
import com.khoepv.entity.Authority;
import com.khoepv.entity.Role;



public interface AuthorityDAO extends JpaRepository<Authority, Integer>{
	
	@Query("SELECT DISTINCT ar FROM Authority ar WHERE ar.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts123);

	@Query("SELECT a.role FROM Authority a WHERE a.account.idaccounts = ?1")
	List<Role> findRoleByUsername(String username);

	@Modifying
	 @Transactional
	@Query("DELETE FROM Authority a WHERE a.account.idaccounts = ?1")
	void deletepri(String id);
}
