package com.atcud.icecreamapp.DTO.entities;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.Payment;

import java.sql.Date;

public class CheckoutOrder {
    private Long id;
    private Customer customer;
    private Payment payment;
    private String paymentOption;
    private Date createdDate;
    private String notes;
    private String status;
    private String deliveryDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public String getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setDeliveryDetail(String deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }

    public CheckoutOrder(Long id, Customer customer, Payment payment, String paymentOption, Date createdDate, String notes, String status, String deliveryDetail) {
        this.id = id;
        this.customer = customer;
        this.payment = payment;
        this.paymentOption = paymentOption;
        this.createdDate = createdDate;
        this.notes = notes;
        this.status = status;
        this.deliveryDetail = deliveryDetail;
    }
}
