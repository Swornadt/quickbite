package com.quickbite.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderModel {
	
	private int orderId;
	private int userId;
	private LocalDateTime orderDate;
	private int orderStatus;
	private String orderNote;
	
	
	public OrderModel() {
	}
	
	//Getter / Setter
	public int getOrderId() {
		return this.orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getOrderDate() {
		return this.orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	public int getOrderStatus() {
		return this.orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getOrderNote() {
		return this.orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	
}
