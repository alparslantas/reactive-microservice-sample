package com.alparslan.commonservice.model;

public class Order {

	private String id;
	private String userId;
	private int amount;
	private String orderStatus;

	public Order() {
	}

	public Order(String userId, int amount, String orderStatus) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.orderStatus = orderStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", amount=" + amount + ", orderStatus=" + orderStatus + "]";
	}

}
