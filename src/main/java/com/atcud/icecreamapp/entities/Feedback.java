package com.atcud.icecreamapp.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Long id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	// A feedback belongs only to one order, so the relation is one to one
	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "order_id")
	private RecipeOrder order;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	public Feedback() {
		
	}

	public Feedback(Long id, Long customerId, Long orderId, String details, Timestamp createdDate) {
		super();
		this.id = id;
		this.details = details;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public RecipeOrder getOrder() {
		return order;
	}

	public void setOrder(RecipeOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", details=" + details
				+ ", createdDate=" + createdDate + "]";
	}
	
}
