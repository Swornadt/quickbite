package com.quickbite.model;
import java.io.Serializable;

/**
 * Represents an item added to the shopping cart in the Quickbite system.
 */
public class CartItemModel implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int itemId;
	private int outletId;
	private String outletName;
	private String itemName;
	private String itemImage;
	private double unitPrice;
	private int quantity;
	
	//Constructor
	
	/**
     * Constructor for CartItemModel with essential fields.
     *
     * @param itemId - the unique identifier of the item
     * @param outletId - the outlet where the item belongs
     * @param outletName - the name of the outlet
     * @param itemName - the name of the item
     * @param unitPrice - the price per unit
     * @param quantity - the quantity of the item
     */
	public CartItemModel(int itemId, int outletId, String outletName, String itemName, double unitPrice, int quantity) {
		this.itemId = itemId;
        this.outletId = outletId;
        this.outletName = outletName;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
	}
		
	/**
     * Returns the unique identifier of the item.
     *
     * @return the item ID
     */
	public int getItemId() {
		return this.itemId;
	}
	
	/**
     * Returns the outlet identifier.
     *
     * @return the outlet ID
     */
	public int getOutletId() {
		return this.outletId;
	}
	
	/**
     * Returns the name of the item.
     *
     * @return the item name
     */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
     * Returns the image filename of the item.
     *
     * @return the item image filename
     */
	public String getItemImage() {
		return this.itemImage;
	}
	
	/**
     * Returns the unit price of the item.
     *
     * @return the unit price
     */
	public double getUnitPrice() {
		return this.unitPrice;
	}
	
	/**
     * Returns the quantity of the item in the cart.
     *
     * @return the quantity
     */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
     * Returns the name of the outlet.
     *
     * @return the outlet name
     */
	public String getOutletName() {
		return this.outletName;
	}
	
	// Setters - to manipulate data of exiting records
	/**
     * Sets the unique identifier of the item.
     *
     * @param itemId - the item ID to set
     */
	public void setItemId(int itemId) {
        this.itemId = itemId;
    }

	/**
     * Sets the outlet identifier.
     *
     * @param outletId - the outlet ID to set
     */
    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    /**
     * Sets the name of the item.
     *
     * @param itemName - the item name to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets the image filename of the item.
     *
     * @param itemImage - the item image filename to set
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    /**
     * Sets the unit price of the item.
     *
     * @param unitPrice - the unit price to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity - the quantity to set
     */ 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
    /**
     * Sets the name of the outlet.
     *
     * @param outletName - the outlet name to set
     */
    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }
    
    // Helper Method
    
    /**
     * Increases the quantity of the item in the cart.
     *
     * @param amount - the amount to add to the current quantity
     */
    public void addQuantity(int amount) {
    	this.quantity += amount;
    }
    
	/**
    * Returns the total price for the cart item (unitPrice × quantity).
    *
    * @return the total price
    */
    public double getTotalPrice() {
    	return this.unitPrice * this.quantity;
    }
}
