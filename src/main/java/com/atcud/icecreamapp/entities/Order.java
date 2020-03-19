package com.atcud.icecreamapp.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="order",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<OrderDetail> orderDetails;
	
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "payment_option")
	private String paymentOption;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "delivery_detail")
	private String deliveryDetail;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "status")
	private Short status;
	
	
	public Order() {
		
	}

	public Order(Long id, Long paymentId, String paymentOption, Timestamp createdDate,
			String deliveryDetail, String notes, Short status) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.paymentOption = paymentOption;
		this.createdDate = createdDate;
		this.deliveryDetail = deliveryDetail;
		this.notes = notes;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryDetail() {
		return deliveryDetail;
	}

	public void setDeliveryDetail(String deliveryDetail) {
		this.deliveryDetail = deliveryDetail;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", paymentId=" + paymentId + ", paymentOption="
				+ paymentOption + ", createdDate=" + createdDate + ", deliveryDetail=" + deliveryDetail + ", notes="
				+ notes + ", status=" + status + "]";
	}
	
}
