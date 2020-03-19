package com.atcud.icecreamapp.DTO;

public class OrderDTO {

	private Long id;
	
	private Long customer_id;
	
	private Long payment_id;
	
	private String paymentOption;

	private String createdDate;
	
	private String deliveryDetail;
	
	private String notes;

	private String status;

	public OrderDTO(Long id, Long customer_id, Long payment_id, String paymentOption, String createdDate,
			String deliveryDetail, String notes, String status) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.payment_id = payment_id;
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

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		this.payment_id = payment_id;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
