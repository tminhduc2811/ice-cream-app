package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.OrderDetail;

public interface OrderDetailRepository {

    public List<OrderDetail> findAll();

    Optional<OrderDetail> findById(Long id);

    public OrderDetail save(OrderDetail orderDetail);

    public void delete(OrderDetail orderDetail);

    public void update(OrderDetail orderDetail);

}
