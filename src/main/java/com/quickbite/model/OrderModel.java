package com.quickbite.model;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Represents a customer order in the Quickbite system.
 */
public class OrderModel {
	
	private int orderId;
	private int userId;
	private LocalDateTime orderDate;
	private int orderStatus;
	private String orderNote;
	private int feedbackId;
	private int paymentId;
	
	/**
     * Default constructor for OrderModel.
     */
	public OrderModel() {
	}
	
	//Getter / Setter
	
	/**
     * Returns the unique identifier of the order.
     *
     * @return the order ID
     */
	public int getOrderId() {
		return this.orderId;
	}
	
	/**
     * Sets the unique identifier of the order.
     *
     * @param orderId the order ID to set
     */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
     * Returns the unique identifier of the user who placed the order.
     *
     * @return the user ID
     */
	public int getUserId() {
		return this.userId;
	}
	
	/**
     * Sets the unique identifier of the user who placed the order.
     *
     * @param userId the user ID to set
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
     * Returns the date and time when the order was placed.
     *
     * @return the order date and time
     */
	public LocalDateTime getOrderDate() {
		return this.orderDate;
	}
	
	/**
     * Sets the date when the order was placed.
     *
     * @param orderDate the order date to set
     */
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
     * Returns the date and time when the order was placed.
     *
     * @return the order date and time
     */
	public int getOrderStatus() {
		return this.orderStatus;
	}
	
	/**
     * Returns any special note attached to the order.
     *
     * @return the order note
     */
	public int getFeedbackId() {
	    return feedbackId;
	}

	/**
     * Returns the payment identifier associated with this order.
     *
     * @return the payment ID
     */
	public int getPaymentId() {
	    return paymentId;
	}

	/**
     * Sets the current status of the order.
     *
     * @param orderStatus the order status to set
     */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
     * Returns any special note attached to the order.
     *
     * @return the order note
     */
	public String getOrderNote() {
		return this.orderNote;
	}
	
	/**
     * Sets any special note for the order.
     *
     * @param orderNote the order note to set
     */
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	
	/**
     * Returns a text for the order status.
     *
     * @return "Pending", "Processing", "Completed", or "Unknown"
     */
	public String getStatusText() {
        if (this.orderStatus == 0) return "Pending";
        if (this.orderStatus == 1) return "Processing";
        if (this.orderStatus == 2) return "Completed";
        return "Unknown";
    }
	
	/**
     * Converts and returns the order date as a  Date object.
     *
     * @return the order date as Date, or null if orderDate is null
     */
	public Date getOrderDateAsDate() {
        if (this.orderDate == null) return null;
        return Date.from(this.orderDate.atZone(ZoneId.systemDefault()).toInstant());
    }
	
	/**
     * Sets the feedback identifier for this order.
     *
     * @param feedbackId the feedback ID to set
     */
	public void setFeedbackId(int feedbackId) {
	    this.feedbackId = feedbackId;
	}
	
	/**
     * Sets the payment identifier for this order.
     *
     * @param paymentId the payment ID to set
     */
	public void setPaymentId(int paymentId) {
	    this.paymentId = paymentId;
	}
	
}
