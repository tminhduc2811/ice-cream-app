package com.atcud.icecreamapp.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "online_order")
public class OnlineOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "online_order")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "book_cost")
	private Float bookCost;
	
	@Column(name = "paying_option")
	private Float payingOption;
	
	@Column(name = "order_date")
	private Timestamp orderDate;
	
	@Column(name = "status")
	private Short status;
	
	public OnlineOrder() {
		
	}

	public OnlineOrder(Long id, String name, String email, String contact, String address, Float bookCost,
			Float payingOption, Timestamp oderDate, Short status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.bookCost = bookCost;
		this.payingOption = payingOption;
		this.orderDate = oderDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getBookCost() {
		return bookCost;
	}

	public void setBookCost(Float bookCost) {
		this.bookCost = bookCost;
	}

	public Float getPayingOption() {
		return payingOption;
	}

	public void setPayingOption(Float payingOption) {
		this.payingOption = payingOption;
	}

	public Timestamp getOderDate() {
		return orderDate;
	}

	public void setOderDate(Timestamp oderDate) {
		this.orderDate = oderDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OnlineOrder [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", address="
				+ address + ", bookCost=" + bookCost + ", payingOption=" + payingOption + ", orderDate=" + orderDate
				+ ", status=" + status + "]";
	}
	
	
}
