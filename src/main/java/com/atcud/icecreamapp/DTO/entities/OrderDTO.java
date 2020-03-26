package com.atcud.icecreamapp.DTO.entities;

import com.atcud.icecreamapp.entities.OrderDetail;

import java.util.List;

public class OrderDTO {

    private Long id;

    private Long customerId;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private String paymentType;

    private String cardNumber;

    private Long paymentId;

    private String paymentOption;

    private String createdDate;

    private String deliveryDetail;

    private Float total;

    private String notes;

    private String status;

    public OrderDTO(Long id, Long customerId, String customerName, String customerPhone, String customerEmail,
                    String paymentType, String cardNumber, Long paymentId, String paymentOption, String createdDate,
                    String deliveryDetail, String notes, String status, List<OrderDetail> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.paymentId = paymentId;
        this.paymentOption = paymentOption;
        this.createdDate = createdDate;
        this.deliveryDetail = deliveryDetail;
        this.notes = notes;
        this.status = status;
        this.total = 0f;
        for (OrderDetail orderDetail : orderDetails) {
            this.total += orderDetail.getPrice() * orderDetail.getQuantity();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}