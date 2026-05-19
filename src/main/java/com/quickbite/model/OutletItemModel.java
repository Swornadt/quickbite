package com.quickbite.model;

/**
 * Represents an item available at a specific outlet with its pricing.
 */
public class OutletItemModel {

	private ItemModel item;
	private OutletModel outlet;
	private double outletItemPrice;
	private int outletId;

	/**
     * Constructor for OutletItemModel with item, outlet and price.
     *
     * @param item - the item associated with the outlet
     * @param outlet - the outlet where the item is available
     * @param outletItemPrice - the price of the item at this outlet
     */
	public OutletItemModel(ItemModel item, OutletModel outlet, double outletItemPrice) {
		this.item = item;
		this.outlet = outlet;
		this.outletItemPrice = outletItemPrice;
	}

	/**
     * Returns the item associated with this outlet.
     *
     * @return the item
     */
	public ItemModel getItem() {
		return item;
	}

	/**
     * Returns the outlet where this item is available.
     *
     * @return the outlet
     */
	public OutletModel getOutlet() {
		return outlet;
	}

	/**
     * Returns the price of the item for an outlet.
     *
     * @return the outlet item price
     */
	public double getOutletItemPrice() {
		return outletItemPrice;
	}

	/**
     * Returns the outlet ID. If the outlet object is present, returns its ID,
     * otherwise returns the stored outletId value.
     *
     * @return the outlet ID
     */
	public int getOutletId() {
		return outlet != null ? outlet.getOutletId() : outletId;
	}

}
