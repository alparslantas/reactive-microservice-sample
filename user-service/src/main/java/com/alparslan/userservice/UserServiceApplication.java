package com.alparslan.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@EnableEurekaClient
@SpringBootApplication
@EnableBinding(Processor.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner addOrder(UserRepository repository) {
		return (args) -> {

			repository.deleteAll();

			User user1 = new User("alptas", "alp", "tas");
			User user2 = new User("arslantas", "arslan", "tas");
			
			List<User> users = new ArrayList<User>();
			users.add(user1);
			users.add(user2);

			repository.saveAll(users).subscribe();
		};
	}
	*/
}
