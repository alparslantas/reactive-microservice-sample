package com.alparslan.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@EnableEurekaClient
@SpringBootApplication
@EnableBinding(Processor.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner addOrder(OrderRepository repository) {
		return (args) -> {

			repository.deleteAll();
			
			Order order1 = new Order("alptas", 123, OrderStatus.ACCEPTED.getText().toLowerCase());
			Order order2 = new Order("arslantas", 456, OrderStatus.PREPARING.getText().toLowerCase());

			List<Order> orders = new ArrayList<Order>();
			orders.add(order1);
			orders.add(order2);

			repository.saveAll(orders).subscribe();
		};
	}
	*/
}
