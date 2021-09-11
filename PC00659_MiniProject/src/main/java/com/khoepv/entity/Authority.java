package com.khoepv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "account_idaccounts", "role_idroles" }) })
public class Authority implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idauthorities;
	@ManyToOne()
	@JoinColumn(name = "account_idaccounts")
	private Account account;
	@ManyToOne()
	@JoinColumn(name = "role_idroles")
	private Role role;
}