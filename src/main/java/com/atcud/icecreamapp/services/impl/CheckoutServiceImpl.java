package com.atcud.icecreamapp.services.impl;

import com.atcud.icecreamapp.DTO.entities.Checkout;
import com.atcud.icecreamapp.DTO.entities.CheckoutOrderDetail;
import com.atcud.icecreamapp.entities.*;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.repositories.customer.CustomerRepository;
import com.atcud.icecreamapp.repositories.order.OrderRepository;
import com.atcud.icecreamapp.repositories.recipe.RecipeRepository;
import com.atcud.icecreamapp.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createOrder(Checkout checkoutRequest) {
        Order order = new Order();
        Customer customer = customerRepository.findCustomerByUsername(checkoutRequest.getOrder().getCustomer().getUserName());
        if (customer == null) {
            throw new CustomException("Customer not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CheckoutOrderDetail orderDetail: checkoutRequest.getOrderDetails()) {
            Optional<Recipe> recipe = recipeRepository.findById(orderDetail.getRecipeId());
            if (recipe.isPresent()) {
                OrderDetail tempDetail = new OrderDetail();
                tempDetail.setRecipe(recipe.get());
                tempDetail.setQuantity(orderDetail.getQuantity());
                tempDetail.setTotal(orderDetail.getTotal());
                tempDetail.setOrder(order);
                // Add order detail to list of oder detail
                orderDetails.add(tempDetail);
            } else {
                throw new CustomException("Request not found", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        order.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer);
        order.setDeliveryDetail(checkoutRequest.getOrder().getDeliveryDetail());
        order.setNotes(checkoutRequest.getOrder().getNotes());
        order.setOrderDetails(orderDetails);
        order.setPaymentOption(checkoutRequest.getOrder().getPaymentOption());
        order.setStatus(checkoutRequest.getOrder().getStatus());
        if (checkoutRequest.getOrder().getPayment() != null) {
            Payment payment = new Payment();
            payment.setCardNumber(checkoutRequest.getOrder().getPayment().getCardNumber());
            payment.setCardType(checkoutRequest.getOrder().getPayment().getCardType());
            payment.setCvv(passwordEncoder.encode(checkoutRequest.getOrder().getPayment().getCvv()));
            payment.setDateOfBirth(checkoutRequest.getOrder().getPayment().getDateOfBirth());
            payment.setExpiredDate(checkoutRequest.getOrder().getPayment().getExpiredDate());
            payment.setName(checkoutRequest.getOrder().getPayment().getName());
            order.setPayment(payment);
        }
        orderRepository.save(order);
    }
}
