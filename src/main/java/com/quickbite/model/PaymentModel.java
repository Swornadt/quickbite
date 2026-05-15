package com.quickbite.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int paymentId;
    private double amount;
    private String paymentStatus; // {"Pending", "Completed", "Failed"}
    private Timestamp paymentDate;

    public PaymentModel() {
    }

    public PaymentModel(int paymentId, double amount, String paymentStatus, Timestamp paymentDate) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

}