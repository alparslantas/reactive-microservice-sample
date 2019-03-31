package com.alparslan.commonservice.constant;

public enum OrderStatus {
	ACCEPTED("accepted"), PREPARING("preparing"), ONWAY("onway"), COMPLETED("completed");

	private String text;

	private OrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
