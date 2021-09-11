package com.khoepv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.khoepv.entity.Order;



public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.account.idaccounts=?1")
	List<Order> findByUsername(String username);

	@Query(value = "select COUNT(o.idorders)\r\n"
			+ "from Orders o join Accounts cc on o.account_idaccount = cc.idaccounts\r\n"
			+ "where cc.idaccounts = ?1", nativeQuery = true)
	int sumByUsername(String username);

	@Query(value = "select Sum(cc.Quantity)\r\n"
			+ "from Orders o join OrderDetails cc on o.idorders = cc.order_idorder join Accounts acc on o.account_idaccount = acc.idaccounts\r\n"
			+ "where acc.idaccounts = ?1", nativeQuery = true)
	int sumqtyByUsername(String username);
	
	@Query(value = "select Sum(cc.Price)\r\n"
			+ "from Orders o join OrderDetails cc on o.idorders = cc.order_idorder join Accounts acc on o.account_idaccount = acc.idaccounts\r\n"
			+ "where acc.idaccounts = ?1", nativeQuery = true)
	Double sumpriByUsername(String username);

}
