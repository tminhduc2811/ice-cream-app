package com.atcud.icecreamapp.DTO.entities;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.Order;

import java.sql.Timestamp;

public class FeedbackDTO {
    private Long id;

//    private CustomerDTO customer;

    private OrderDTO order;

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
//
//    public CustomerDTO getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(CustomerDTO customer) {
//        this.customer = customer;
//    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public FeedbackDTO() {

    }

}
