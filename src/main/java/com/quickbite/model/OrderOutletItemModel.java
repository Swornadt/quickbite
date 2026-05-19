package com.quickbite.model;

import java.sql.Timestamp;

/**
 * Represents an order item linked with outlet-specific details
 */
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

	 
	 /**
	     * Constructor for OrderOutletItemModel with all fields initialized.
	     *
	     * @param orderId - the unique identifier of the order
	     * @param outletId - the outlet where the order belongs
	     * @param itemId - the item identifier
	     * @param itemStatus - the status of the item in the order
	     * @param itemName - the name of the item
	     * @param itemQty - the quantity of the item
	     * @param orderStatus - the current status of the order
	     * @param orderDate -  the timestamp when the order was placed
	     * @param preferredDate - the preferred delivery date (null for instant)
	     * @param orderNote - any special note for the order
	     * @param outletName - the name of the outlet
	     * @param outletOrderStatus - the status of the order at the outlet level
	     */
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
	 
	 
	 /**
     * Overloading for distinct
     *
     * @param orderId - the unique identifier of the order
     * @param outletId - the outlet identifier
     * @param outletOrderStatus - the status of the order at the outlet level
     */
	 public OrderOutletItemModel(int orderId, int outletId, int outletOrderStatus) {
		    this.orderId = orderId;
		    this.outletId = outletId;
		    this.outletOrderStatus = outletOrderStatus;
	}
	
	 /**
	     * Returns the unique identifier of the order.
	     *
	     * @return the order ID
	     */
	 public int getOrderId() {
		 return orderId; 
	 }
	 
	 /**
	     * Returns the outlet identifier.
	     *
	     * @return the outlet ID
	     */
	 public int getOutletId() {
		 return outletId; 
	 }
	 
	 /**
	 * Returns the item identifier.
	 *
	 * @return the item ID
	 */	 
	 public int getItemId() { 
		 return itemId; 
	 }
	 
	 /**
	     * Returns the status of the item within the order.
	     *
	     * @return the item status
	     */	 
	 public int getItemStatus() { 
		 return itemStatus; 
	 }
	 
	 /**
	     * Returns the name of the item.
	     *
	     * @return the item name
	     */
	 public String getItemName() { 
		 return itemName; 
	 }

	 /**
	     * Returns the quantity of the item ordered.
	     *
	     * @return the item quantity
	     */
	 public int getItemQty() { 
		 return itemQty; 
	 }
	 
	 /**
	     * Returns the current status of the order.
	     *
	     * @return the order status
	     */
	 public int getOrderStatus() { 
		 return orderStatus; 
	 }
	 
	 /**
	     * Returns the timestamp when the order was placed.
	     *
	     * @return the order date
	     */
	 public Timestamp getOrderDate() { 
		 return orderDate; 
	 }
	 
	 /**
	     * Returns the preferred delivery date.
	     *
	     * @return the preferred date 
	     */ 
	 public Timestamp getPreferredDate() { 
		 return preferredDate; 
	 }
	 
	 /**
	     * Returns any special note attached to the order.
	     *
	     * @return the order note
	     */	 
	 public String getOrderNote() { 
		 return orderNote; 
	 }

	 /**
	     * Returns the name of the outlet.
	     *
	     * @return the outlet name
	     */
	 public String getOutletName() { 
		 return outletName; 
	 }

	 /**
	     * Returns the outlet-level order status.
	     *
	     * @return the outlet order status
	     */
	 public int getOutletOrderStatus() {
		 return outletOrderStatus;
	 }
	
	 /**
	     * Returns a label for the order status.
	     *
	     * @return "Pending", "Ongoing", "Complete", or "Unknown"
	     */
	 public String getOrderStatusLabel() {
		 switch (orderStatus) {
		   	case 0: return "Pending";
		    case 1: return "Ongoing";
		    case 2: return "Complete";
		    default: return "Unknown";
		   }
	}

	 /**
	     * Returns the order type based on preferred date.
	     *
	     * @return "Instant Delivery" if preferredDate is null, otherwise "Scheduled"
	     */
	public String getOrderTypeLabel() {    
		return preferredDate == null ? "Instant Delivery" : "Scheduled";	
	}
	
	/**
     * Sets the status of the order.
     *
     * @param orderStatus the order status to set
     */
	 public void setOrderStatus(int orderStatus) { 
		 this.orderStatus = orderStatus; 
	 }
	 
	 /**
	     * Returns a human-readable label for the outlet order status.
	     *
	     * @return "Pending", "Ongoing", "Complete", or "Unknown"
	     */
	 public String getOutletOrderStatusLabel() {
		    switch (outletOrderStatus) {
		        case 0: return "Pending";
		        case 1: return "Ongoing";
		        case 2: return "Complete";
		        default: return "Unknown";
		    }
		}
	 
}
	 