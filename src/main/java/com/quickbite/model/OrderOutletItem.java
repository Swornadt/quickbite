package com.quickbite.model;

public class OrderOutletItem {
	 private int orderId;
	 private int outletId;
	 private int itemId;
	 private int itemQty;
	 private double orderSubtotal;

	 public OrderOutletItem(int orderId, int outletId, int itemId, int itemQty, double orderSubtotal) {
		 this.orderId = orderId;
		 this.outletId = outletId;
		 this.itemId = itemId;
		 this.itemQty = itemQty;
		 this.orderSubtotal = orderSubtotal;
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

	 public void setOrderId(int orderId) {
		 this.orderId = orderId;
	 }
	 
	 public void setOutletId(int outletId) {
		 this.outletId = outletId; 
	 }
	 
	 public void setItemId(int itemId) {
		 this.itemId = itemId; 
	 }
	 
	 public void setItemQty(int itemQty) {
		 this.itemQty = itemQty;
	 }
	 
	 public void setOrderSubtotal(double orderSubtotal) {
		 this.orderSubtotal = orderSubtotal;
	 }
}   
	 