package com.atcud.icecreamapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcud.icecreamapp.entities.OnlineOrder;

public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Long> {

}
