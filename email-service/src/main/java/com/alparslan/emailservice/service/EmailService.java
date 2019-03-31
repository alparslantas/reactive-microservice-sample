package com.alparslan.emailservice.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alparslan.commonservice.model.Order;

@Service
public class EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Value("${alp.mail.to}")
	private String mailTo;

	@Autowired
	private JavaMailSender sender;

	public void sendEmail(Order order) throws MessagingException {
		LOGGER.info("sendEmail: order={}", order);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(mailTo);
		helper.setSubject("Order Created by " + order.getUserId());
		helper.setText(order.toString());

		sender.send(message);

	}

}
