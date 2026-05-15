package com.quickbite.model;

public class OutletModel {
	private int outletId;
	private String outletName;
	private String outletStatus;
	private String outletImage;

	//Constructors
	public OutletModel(int outletId, String outletName, String outletStatus, String outletImage) {
		this.outletId=outletId;
		this.outletName=outletName;
		this.outletStatus=outletStatus;
		this.outletImage=outletImage;
	}
	
	//Getter Methods
	public int getOutletId() {
		return outletId;
	}
	
	public String getOutletName() {
		return outletName;
	}
	
	public String getOutletStatus() {
		return outletStatus;
	}
	
	public String getOutletImage() {
		return outletImage;
	}
	
	
	//Setter Methods
	public void setOutletId(int outletId) {
		this.outletId=outletId;
	}
	
	public void setOutletName(String outletName) {
		this.outletName=outletName;
	}
	
	public void setOutletStatus(String outletStatus) {
		this.outletStatus=outletStatus;
	}
	
	public void setOutletImage(String outletImage) {
		this.outletImage=outletImage;
	}
}
