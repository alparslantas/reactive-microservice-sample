package com.alparslan.orders.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.alparslan.commonservice.model.Order;

import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

	Flux<Order> findByUserId(String username);
}