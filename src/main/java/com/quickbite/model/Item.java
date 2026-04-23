package com.quickbite.model;

public class Item {
	
	private String itemName;
	private String category;
	private String itemType;
	private String itemStatus;
	private String itemIngredient;
	private String itemAllergy;
	private String itemImage;
	private double itemPrice;
	
	//Constructor
	public Item(String itemName, String category, String itemType, String itemStatus, String itemIngredient, String itemAllergy, String itemImage, double itemPrice) {
	    this.itemName = itemName;
	    this.category = category;
	    this.itemType = itemType;
	    this.itemStatus = itemStatus;
	    this.itemIngredient = itemIngredient;
	    this.itemAllergy = itemAllergy;
	    this.itemImage = itemImage;
	    this.itemPrice = itemPrice;
		}
	
	//Getters - to retrieve data from DB
	public String getItemName() {
		return itemName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getItemType() {
		return itemType;
	}
	
	public String getItemStatus() {
		return itemStatus;
	}
	
	public String getItemIngredient() {
		return itemIngredient;
	}
	
	public String getItemAllergy() {
		return itemAllergy;
	}
	
	public String getItemImage() {
		return itemImage;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}
	
	// Setters - to manipulate data of exiting records
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public void setItemIngredient(String itemIngredient) {
		this.itemIngredient = itemIngredient;
	}
	
	public void setItemAllergy(String itemAllergy) {
		this.itemAllergy = itemAllergy;
	}
	
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
