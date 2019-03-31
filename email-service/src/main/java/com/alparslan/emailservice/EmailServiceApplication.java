package com.alparslan.emailservice;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.alparslan.commonservice.model.Order;
import com.alparslan.emailservice.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Processor.class)
public class EmailServiceApplication {
	
	@Autowired
	private EmailService emailService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceApplication.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	@StreamListener(Processor.INPUT)
	public void receiveOrder(Order order) throws JsonProcessingException, MessagingException {
		LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
		emailService.sendEmail(order);
	}

}
