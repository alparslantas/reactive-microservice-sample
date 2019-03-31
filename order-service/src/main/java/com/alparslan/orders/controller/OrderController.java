package com.alparslan.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alparslan.commonservice.model.Order;
import com.alparslan.orders.messaging.OrderNotifier;
import com.alparslan.orders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderRepository repository;

	@Autowired
	OrderNotifier orderNotifier;

	private ObjectMapper mapper = new ObjectMapper();

	@GetMapping
	public Flux<Order> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{orderId}")
	public Mono<Order> findById(@PathVariable("orderId") String orderId) {
		LOGGER.info("findByOrderId: orderId={}", orderId);
		return repository.findById(orderId);
	}

	@GetMapping("/{userId}/orders")
	public Flux<Order> findByUser(@PathVariable("userId") String userId) {
		LOGGER.info("findByUser: userId={}", userId);
		return repository.findByUserId(userId);
	}

	@PostMapping
	public Mono<Order> createOrder(@RequestBody Order order) throws JsonProcessingException {
		Mono<Order> resp = repository.save(order);
		LOGGER.info("Order saved: {}", mapper.writeValueAsString(order));
		resp.subscribe(o-> orderNotifier.notify(o));
		return resp;
	}

	@PutMapping
	public Mono<Order> updateOrder(@RequestBody Order order) throws JsonProcessingException {
		Mono<Order> resp = repository.save(order);
		LOGGER.info("Order updated: {}", mapper.writeValueAsString(order));
		orderNotifier.notify(order);
		return resp;
	}

	@DeleteMapping
	public void deleteOrder(@RequestBody Order order) throws JsonProcessingException {
		repository.delete(order);
		LOGGER.info("Order delete: {}", mapper.writeValueAsString(order));
		orderNotifier.notify(order);
	}

}
