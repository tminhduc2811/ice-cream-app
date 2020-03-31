package com.atcud.icecreamapp.DTO.entities;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.Order;

import java.sql.Timestamp;

public class FeedbackDTO {
    private Long id;

//    private Customer customer;

    private Order order;

    private String details;

    private Timestamp createdDate;

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

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FeedbackDTO() {

    }

    public FeedbackDTO(Long id, Order order, String details, Timestamp createdDate) {
        this.id = id;
        this.order = order;
        this.details = details;
        this.createdDate = createdDate;
    }
}
