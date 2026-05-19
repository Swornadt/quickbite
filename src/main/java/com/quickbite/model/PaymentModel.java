package com.quickbite.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int paymentId;
    private double amount;
    private String paymentStatus; // {"Pending", "Completed", "Failed"}
    private Timestamp paymentDate;

    /**
     * Default constructor for PaymentModel.
     */
    public PaymentModel() {
    }

    /**
     * Constructs a PaymentModel with all fields initialized
     * @param paymentId - the unique identifier of the payment
     * @param amount - the payment amount
     * @param paymentStatus - the status of payment 
     * @param paymentDate - the timestamp when the payment was made
     */
    public PaymentModel(int paymentId, double amount, String paymentStatus, Timestamp paymentDate) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    /**
     * Returns the unique identifier of the payment.
     *
     * @return the payment ID
     */   
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique identifier of the payment.
     *
     * @param paymentId the payment ID to set
     */

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Returns the payment amount.
     *
     * @return the amount
     */

    public double getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount.
     *
     * @param amount the amount to set
     */   
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the status of the payment.
     *
     * @return the payment status
     */  
    public String getPaymentStatus() {
        return paymentStatus;
    }


    /**
     * Sets the status of the payment.
     *
     * @param paymentStatus the payment status to set
     */    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Returns the timestamp of when the payment was made.
     *
     * @return paymentDate 
     */
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the timestamp of when the payment was made.
     *
     * @param paymentDate the payment date to set
     */
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

}