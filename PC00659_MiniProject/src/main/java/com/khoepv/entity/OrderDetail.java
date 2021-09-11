package com.khoepv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "orderdetails")
public class OrderDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long idorderdetails;
	Double price;
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "product_idproduct")
	Product product;
	@ManyToOne
	@JoinColumn(name = "order_idorder")
	Order order;
}
