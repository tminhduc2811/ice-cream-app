package com.atcud.icecreamapp.DTO.entities;

import java.util.List;

public class Checkout {
    private CheckoutOrder order;
    private List<CheckoutOrderDetail> orderDetails;

    public CheckoutOrder getOrder() {
        return order;
    }

    public void setOrder(CheckoutOrder order) {
        this.order = order;
    }

    public List<CheckoutOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CheckoutOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Checkout(CheckoutOrder order, List<CheckoutOrderDetail> orderDetails) {
        this.order = order;
        this.orderDetails = orderDetails;
    }
}

