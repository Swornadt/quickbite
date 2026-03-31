package com.quickbite.model;

public class Item {
	private String itemName;
	private String category;
	private String itemType;
	private String itemStatus;
	private String itemIngredient;
	private String itemAllergy;
	private String itemImage;
	
	//Getters
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
	
	// Setters
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
	
}
