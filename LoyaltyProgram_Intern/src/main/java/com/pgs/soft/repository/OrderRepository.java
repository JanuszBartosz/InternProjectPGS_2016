package com.pgs.soft.repository;

import org.springframework.data.repository.CrudRepository;

import com.pgs.soft.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
