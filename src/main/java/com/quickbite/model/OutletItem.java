package com.quickbite.model;

public class OutletItem {
	
	private Item item;
	private double outletItemPrice;
	
	public OutletItem(Item item, double outletItemPrice) {
		this.item = item;
		this.outletItemPrice = outletItemPrice;
	}
	
	public Item getItem() {
		return item;
	}
	
	public double getOutletItemPrice(){
		return outletItemPrice;
	}
	
}
