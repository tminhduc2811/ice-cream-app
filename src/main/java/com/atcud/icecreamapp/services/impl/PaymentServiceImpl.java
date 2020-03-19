package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Payment;
import com.atcud.icecreamapp.repositories.PaymentRepository;
import com.atcud.icecreamapp.services.PaymentService;

@Component
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> getAllPayment() {
		return paymentRepository.findAll();
	}

	@Override
	public Optional<Payment> getPaymentById(Long id) {
		return paymentRepository.findById(id);
	}

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
	}

	@Override
	public void update(Payment payment) {
		paymentRepository.update(payment);
	}

}
