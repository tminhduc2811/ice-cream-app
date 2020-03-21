package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.OrderDetail;

public interface OrderDetailService {

    public List<OrderDetail> getAllOrderDetails();

    public Optional<OrderDetail> getOrderDetailById(Long id);

    public OrderDetail save(OrderDetail orderDetail);

    public void delete(OrderDetail orderDetail);

    public void update(OrderDetail orderDetail);

}
