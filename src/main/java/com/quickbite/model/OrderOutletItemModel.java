package com.quickbite.model;

import java.sql.Timestamp;

public class OrderOutletItemModel {
	 private int orderId;
	 private int outletId;
	 private int itemId;
	 private int itemStatus;
	 private String itemName;
	 private int itemQty;
	 private int orderStatus;
	 private Timestamp orderDate;
	 private Timestamp preferredDate;
	 private String orderNote;
	 private String outletName;
	 private int outletOrderStatus;

	 
	 public OrderOutletItemModel(int orderId, int outletId, int itemId, int itemStatus, String itemName, int itemQty, int orderStatus, 
			 Timestamp orderDate, Timestamp preferredDate, String orderNote, String outletName, int outletOrderStatus) {
		 	this.orderId = orderId;
		    this.outletId = outletId;
		    this.itemId = itemId;
		    this.itemQty = itemQty;
		    this.itemStatus = itemStatus;
		    this.itemName = itemName;
		    this.orderStatus = orderStatus;
		    this.orderDate = orderDate;
		    this.preferredDate = preferredDate;
		    this.orderNote = orderNote;
		    this.outletName = outletName; 
		    this.outletOrderStatus = outletOrderStatus;
		
	}
	 
	 //Overloading for distinct
	 public OrderOutletItemModel(int orderId, int outletId, int outletOrderStatus) {
		    this.orderId = orderId;
		    this.outletId = outletId;
		    this.outletOrderStatus = outletOrderStatus;
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
	 
	 public int getItemStatus() { 
		 return itemStatus; 
	 }
	 
	 public String getItemName() { 
		 return itemName; 
	 }

	 public int getItemQty() { 
		 return itemQty; 
	 }
	 
	 public int getOrderStatus() { 
		 return orderStatus; 
	 }
	 
	 public Timestamp getOrderDate() { 
		 return orderDate; 
	 }
	 
	 public Timestamp getPreferredDate() { 
		 return preferredDate; 
	 }
	 
	 public String getOrderNote() { 
		 return orderNote; 
	 }

	 public String getOutletName() { 
		 return outletName; 
	 }

	 public int getOutletOrderStatus() {
		 return outletOrderStatus;
	 }
	 
	 public String getOrderStatusLabel() {
		 switch (orderStatus) {
		   	case 0: return "Pending";
		    case 1: return "Ongoing";
		    case 2: return "Complete";
		    default: return "Unknown";
		   }
	}

	public String getOrderTypeLabel() {    
		return preferredDate == null ? "Instant Delivery" : "Scheduled";	
	}
	 
	 public void setOrderStatus(int orderStatus) { 
		 this.orderStatus = orderStatus; 
	 }
	 
	 public String getOutletOrderStatusLabel() {
		    switch (outletOrderStatus) {
		        case 0: return "Pending";
		        case 1: return "Ongoing";
		        case 2: return "Complete";
		        default: return "Unknown";
		    }
		}
	 
}
	 