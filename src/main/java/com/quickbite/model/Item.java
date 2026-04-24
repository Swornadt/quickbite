package com.quickbite.model;

public class Item {
	
	private int itemId;           
    private String itemName;
    private String category;
    private String itemType;
    private String itemDescription; 
    private String itemStatus;
    private String itemIngredient;
    private String itemAllergy;
    private String itemImage;
	
	//Constructor
    public Item(int itemId, String itemName, String category, String itemType,
            String itemDescription, String itemStatus, String itemIngredient,
            String itemAllergy, String itemImage) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.category = category;
    this.itemType = itemType;
    this.itemDescription = itemDescription;
    this.itemStatus = itemStatus;
    this.itemIngredient = itemIngredient;
    this.itemAllergy = itemAllergy;
    this.itemImage = itemImage;
}
    
    //Without ItemId for adding items
    public Item(String itemName, String category, String itemType,
            String itemDescription, String itemStatus, String itemIngredient,
            String itemAllergy, String itemImage) {
    this.itemName = itemName;
    this.category = category;
    this.itemType = itemType;
    this.itemDescription = itemDescription;
    this.itemStatus = itemStatus;
    this.itemIngredient = itemIngredient;
    this.itemAllergy = itemAllergy;
    this.itemImage = itemImage;
}
	
	//Getters - to retrieve data from DB
    public int getItemId() { 
    	return itemId; 
    }
    
    public String getItemName() {
		return itemName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getItemType() {
		return itemType;
	}
	
	 public String getItemDescription() { 
		 return itemDescription; 
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
	
	public void setItemDescription(String itemDescription) { 
		this.itemDescription = itemDescription; 
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
