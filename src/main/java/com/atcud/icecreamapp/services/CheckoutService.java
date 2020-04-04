package com.atcud.icecreamapp.services;

import com.atcud.icecreamapp.DTO.entities.Checkout;

public interface CheckoutService {
    void createOrder(Checkout checkoutRequest);
}
