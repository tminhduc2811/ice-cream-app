package com.atcud.icecreamapp.DTO.entities;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.Order;

import javax.persistence.*;
import java.sql.Timestamp;

public class FeedbackDTO {
    private Long id;

    private String customerName;

    private Long orderId;

    private String details;

    private Timestamp createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public FeedbackDTO(Long id, String customerName, Long orderId, String details, Timestamp createdDate) {
        this.id = id;
        this.customerName = customerName;
        this.orderId = orderId;
        this.details = details;
        this.createdDate = createdDate;
    }
}
