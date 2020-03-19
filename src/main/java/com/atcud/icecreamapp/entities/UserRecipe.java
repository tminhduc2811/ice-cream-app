//package com.atcud.icecreamapp.entities;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//@Table(name = "user_recipe")
//public class UserRecipe {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_recipe_id")
//	private Long id;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "image")
//	private String image;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@Column(name = "details")
//	private String details;
//	
//	@ManyToOne(
//			fetch = FetchType.LAZY,
//			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.DETACH, CascadeType.REFRESH})
//	@JoinColumn(name = "customer_id")
//	@JsonIgnore
//	private Customer customer;
//	
//	@Column(name = "prize_status")
//	private Short prizeStatus;
//	
//	@Column(name = "enable_status")
//	private Short enableStatus;
//	
//	public UserRecipe() {
//		
//	}
//	
//
//	public UserRecipe(Long id, String name, String image, String description, String details, Customer customer,
//			Short prizeStatus, Short enableStatus) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.image = image;
//		this.description = description;
//		this.details = details;
//		this.customer = customer;
//		this.prizeStatus = prizeStatus;
//		this.enableStatus = enableStatus;
//	}
//
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getDetails() {
//		return details;
//	}
//
//	public void setDetails(String details) {
//		this.details = details;
//	}
//
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//
//	public Short getPrizeStatus() {
//		return prizeStatus;
//	}
//
//	public void setPrizeStatus(Short prizeStatus) {
//		this.prizeStatus = prizeStatus;
//	}
//
//	public Short getEnableStatus() {
//		return enableStatus;
//	}
//
//	public void setEnableStatus(Short enableStatus) {
//		this.enableStatus = enableStatus;
//	}
//
//
//	@Override
//	public String toString() {
//		return "UserRecipe [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description
//				+ ", details=" + details + ", customer=" + customer + ", prizeStatus=" + prizeStatus + ", enableStatus="
//				+ enableStatus + "]";
//	}
//
//
//}
