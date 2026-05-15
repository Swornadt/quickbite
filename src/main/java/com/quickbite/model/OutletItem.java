package com.quickbite.model;

public class OutletItem {

	private ItemModel item;
	private Outlet outlet;
	private double outletItemPrice;
	private int outletId;

	public OutletItem(ItemModel item, Outlet outlet, double outletItemPrice) {
		this.item = item;
		this.outlet = outlet;
		this.outletItemPrice = outletItemPrice;
	}

	public ItemModel getItem() {
		return item;
	}

	public Outlet getOutlet() {
		return outlet;
	}

	public double getOutletItemPrice() {
		return outletItemPrice;
	}

	public int getOutletId() {
		return outlet != null ? outlet.getOutletId() : outletId;
	}

}
