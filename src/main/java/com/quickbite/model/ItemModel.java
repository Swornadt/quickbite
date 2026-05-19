package com.quickbite.model;

/**
 * Represents a food item in the Quickbite system.
 */
public class ItemModel {
	
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
    
    /**
     * Constructor for ItemModel with all fields initialized.
     *
     * @param itemId - the unique identifier of the item
     * @param itemName - the name of the item
     * @param category - the category of the item
     * @param itemType - the type of the item
     * @param itemDescription - the description of the item
     * @param itemStatus - the current status of the item
     * @param itemIngredient - the ingredients of the item
     * @param itemAllergy - allergy information for the item
     * @param itemImage - the image filename of the item
     */
    public ItemModel(int itemId, String itemName, String category, String itemType,
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
    
    /**
     * Constructor for ItemModel without itemId (used for adding new items).
     *
     * @param itemName - the name of the item
     * @param category - the category of the item
     * @param itemType - the type of the item
     * @param itemDescription - the description of the item
     * @param itemStatus - the current status of the item
     * @param itemIngredient - the ingredients of the item
     * @param itemAllergy - allergy information for the item
     * @param itemImage - the image filename of the item
     */     
    public ItemModel(String itemName, String category, String itemType,
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
    
    public ItemModel() {
    	
    }
	
	//Getters - to retrieve data from DB
    
    /**
     * Returns the unique identifier of the item.
     *
     * @return the item ID
     */
    public int getItemId() { 
    	return itemId; 
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
     * Returns the category of the item.
     *
     * @return the item category
     */
	public String getCategory() {
		return category;
	}
	
	/**
     * Returns the type of the item.
     *
     * @return the item type
     */
	public String getItemType() {
		return itemType;
	}
	
	/**
     * Returns the description of the item.
     *
     * @return the item description
     */
	 public String getItemDescription() { 
		 return itemDescription; 
	 }
	
	 /**
	     * Returns the current status of the item.
	     *
	     * @return the item status
	     */	 
	public String getItemStatus() {
		return itemStatus;
	}
	
	/**
     * Returns the ingredients of the item.
     *
     * @return the item ingredients
     */
	public String getItemIngredient() {
		return itemIngredient;
	}
	
	/**
     * Returns the allergy information of the item.
     *
     * @return the item allergy information
     */
	public String getItemAllergy() {
		return itemAllergy;
	}
	
	/**
     * Returns the image filename of the item.
     *
     * @return the item image filename
     */
	public String getItemImage() {
		return itemImage;
	}
	

	
	// Setters - to manipulate data of exiting records
	
	/**
     * Sets the unique identifier of the item.
     *
     * @param itemId the item ID to set
     */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	/**
     * Sets the name of the item.
     *
     * @param itemName the item name to set
     */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
     * Sets the category of the item.
     *
     * @param category the item category to set
     */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
     * Sets the type of the item.
     *
     * @param itemType the item type to set
     */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	/**
     * Sets the description of the item.
     *
     * @param itemDescription the item description to set
     */
	public void setItemDescription(String itemDescription) { 
		this.itemDescription = itemDescription; 
	}
	
	/**
     * Sets the status of the item.
     *
     * @param itemStatus the item status to set
     */
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	/**
     * Sets the ingredients of the item.
     *
     * @param itemIngredient the item ingredients to set
     */
	public void setItemIngredient(String itemIngredient) {
		this.itemIngredient = itemIngredient;
	}
	
	/**
     * Sets the allergy information of the item.
     *
     * @param itemAllergy the item allergy information to set
     */
	public void setItemAllergy(String itemAllergy) {
		this.itemAllergy = itemAllergy;
	}
	
	/**
     * Sets the image filename of the item.
     *
     * @param itemImage the item image filename to set
     */
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	

	
}
