package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Payment;

public interface PaymentRepository {

    public List<Payment> findAll();

    public Optional<Payment> findById(Long id);

    Payment save(Payment payment);

    public void delete(Payment payment);

    public void update(Payment payment);

}
