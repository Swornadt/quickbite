package com.quickbite.model;
import java.io.Serializable;

public class CartItemModel implements Serializable {

	private int itemId;
	private int outletId;
	private String itemName;
	private String itemImage;
	private double unitPrice;
	private int quantity;
	
	//Constructor
	public CartItemModel() {
		this.itemId = itemId;
        this.outletId = outletId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
	}
	
	//Getters - to retrieve data from DB
	public int getItemId() {
		return this.itemId;
	}
	
	public int getOutletId() {
		return this.outletId;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public String getItemImage() {
		return this.itemImage;
	}
	
	public double getUnitPrice() {
		return this.unitPrice;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	// Setters - to manipulate data of exiting records
	public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	
}
