package com.quickbite.model;

public class OrderOutletItem {
	 private int orderId;
	 private int outletId;
	 private int itemId;
	 private int itemQty;
	 private double orderSubtotal;
	 private int orderStatus;

	 public OrderOutletItem(int orderId, int outletId, int itemId, int itemQty, double orderSubtotal, int orderStatus) {
		 this.orderId = orderId;
		 this.outletId = outletId;
		 this.itemId = itemId;
		 this.itemQty = itemQty;
		 this.orderSubtotal = orderSubtotal;
		 this.orderStatus = orderStatus;
	 }
	 
	 //Overloading for distinct
	 public OrderOutletItem(int orderId, int outletId, int orderStatus) {
		    this.orderId = orderId;
		    this.outletId = outletId;
		    this.orderStatus = orderStatus;
		}
	 
	 public int getOrderId() {
		 return orderId; 
	 }
	 
	 public int getOutletId() {
		 return outletId; 
	 }
	 
	 public int getItemId() {
		 return itemId; 
	 }
	 
	 public int getItemQty() {
		 return itemQty;
	 }
	 
	 public double getOrderSubtotal() {
		 return orderSubtotal; 
	 }

	 public int getOrderStatus() {
		 return orderStatus; 
	 }
	 
	 public void setOrderStatus(int orderStatus) {
		 this.orderStatus = orderStatus;
	 }
}   
	 