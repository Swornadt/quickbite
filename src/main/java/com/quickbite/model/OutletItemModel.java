package com.quickbite.model;

public class OutletItemModel {

	private ItemModel item;
	private OutletModel outlet;
	private double outletItemPrice;
	private int outletId;

	public OutletItemModel(ItemModel item, OutletModel outlet, double outletItemPrice) {
		this.item = item;
		this.outlet = outlet;
		this.outletItemPrice = outletItemPrice;
	}

	public ItemModel getItem() {
		return item;
	}

	public OutletModel getOutlet() {
		return outlet;
	}

	public double getOutletItemPrice() {
		return outletItemPrice;
	}

	public int getOutletId() {
		return outlet != null ? outlet.getOutletId() : outletId;
	}

}
