package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.entities.Checkout;
import com.atcud.icecreamapp.entities.OrderDetail;
import com.atcud.icecreamapp.services.CheckoutService;
import com.atcud.icecreamapp.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> registerCustomer(@RequestBody Checkout checkout) {
        checkoutService.createOrder(checkout);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<OrderDetail> get() {
        System.out.println("here");
        return orderDetailService.getAllOrderDetails();
    }
}
