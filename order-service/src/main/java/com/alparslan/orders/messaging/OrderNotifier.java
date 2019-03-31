package com.alparslan.orders.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.alparslan.commonservice.model.Order;

@Service
public class OrderNotifier {

	@Autowired
	private Source source;

	public boolean notify(Order order) {
		return this.source.output().send(MessageBuilder.withPayload(order).build());
	}
}
