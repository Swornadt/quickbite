package com.quickbite.model;
/**
 * Represents the summary report for the admin Dashboard
 */
public class ReportModel {
	private int totalUsers;
	private int totalOrders;
	private double totalSales;
	
	 /**
     * Returns the total number of registered users.
     *
     * @return the total user count
     */
	public int getTotalUsers() {
		return totalUsers;
	}
	
    /**
     * Sets the total number of registered users.
     *
     * @param totalUsers the total user count to set
     */
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	

    /**
     * Returns the total number of orders placed.
     *
     * @return the total order count
     */
	public int getTotalOrders() {
		return totalOrders;
	}
	
	 /**
     * Sets the total number of orders placed.
     *
     * @param totalOrders the total order count to set
     */
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}
	
	  /**
     * Returns the total sales revenue.
     *
     * @return the total sales amount
     */
	public double getTotalSales() {
		return totalSales;
	}
	
    /**
     * Sets the total sales revenue.
     *
     * @param totalSales the total sales amount to set
     */
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
}
