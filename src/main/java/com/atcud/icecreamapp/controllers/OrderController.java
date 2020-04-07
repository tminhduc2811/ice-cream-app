package com.atcud.icecreamapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atcud.icecreamapp.DTO.entities.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(orderService.findPage(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> createFAQ(@RequestBody Order order) {
        order = orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> feedback = orderService.getOptionalOrderById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(feedback.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Order> update(@RequestBody Order newOrder) {
        orderService.update(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }
}
