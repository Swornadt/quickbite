package com.quickbite.model;

public class OutletItem {
	
	private Item item;
	private Outlet outlet;
	private double outletItemPrice;
	
	
	public OutletItem(Item item, Outlet outlet, double outletItemPrice) {
		this.item = item;
        this.outlet = outlet;
        this.outletItemPrice = outletItemPrice;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Outlet getOutlet() {
		return outlet;
	}
	
	public double getOutletItemPrice(){
		return outletItemPrice;
	}
	
}
