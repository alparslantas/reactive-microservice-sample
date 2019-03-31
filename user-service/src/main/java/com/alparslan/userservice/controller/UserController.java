package com.alparslan.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.alparslan.commonservice.model.Order;
import com.alparslan.userservice.model.User;
import com.alparslan.userservice.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository repository;

	WebClient webClient = WebClient.create("http://localhost:9999");

	@GetMapping
	public Flux<User> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{userId}")
	public Mono<User> findById(@PathVariable("userId") String userId) {
		LOGGER.info("findByUserId: userId={}", userId);
		return repository.findByUserId(userId);
	}

	@PostMapping
	public Mono<User> createUser(@RequestBody User user) {
		LOGGER.info("create: {}", user);
		return repository.findByUserId(user.getUserId()).switchIfEmpty(repository.save(user));
	}

	/**** ORDER SERVICE OPERATIONS ***/

	@GetMapping("/{userId}/orders")
	public Flux<Order> findOrdersByUserId(@PathVariable("userId") String userId) {
		LOGGER.info("findOrderById: id={}", userId);
		Flux<Order> orders = webClient.get().uri("order-service/{userId}/orders", userId).retrieve()
				.bodyToFlux(Order.class);
		return orders;
	}

	@PostMapping("/{userId}")
	public Mono<Order> createOrder(@RequestBody Order order) {
		LOGGER.info("order created: {}", order);
		Mono<Order> createdOrder = webClient.post().uri("order-service/", order).body(BodyInserters.fromObject(order))
				.retrieve().bodyToMono(Order.class);
		return createdOrder;
	}

	@PutMapping("/{userId}")
	public Mono<Order> updateOrder(@RequestBody Order order) {
		LOGGER.info("updated order: {}", order);
		Mono<Order> updateOrder = webClient.put().uri("order-service/", order).body(BodyInserters.fromObject(order))
				.retrieve().bodyToMono(Order.class);
		return updateOrder;
	}

}
