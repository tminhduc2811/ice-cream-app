package com.atcud.icecreamapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class admin {

	@Id
	@Column(name = "admin")
	private String userName;
	
	@Column(name = "password")
	private String password;
}
