package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Payment;

public interface PaymentService {

    public List<Payment> getAllPayment();

    public Optional<Payment> getPaymentById(Long id);

    public Payment save(Payment payment);

    public void delete(Payment payment);

    public void update(Payment payment);

}
