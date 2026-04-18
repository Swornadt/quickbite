package com.quickbite.model;

public class Outlet {
	private int outlet_id;
	private String outlet_name;
	private String outlet_status;
	private String outlet_image;

	//Constructors
	public Outlet(int outlet_id, String outlet_name, String outlet_status, String outlet_image) {
		this.outlet_id=outlet_id;
		this.outlet_name=outlet_name;
		this.outlet_status=outlet_status;
		this.outlet_image=outlet_image;
	}
	
	//Getter Methods
	public int getOutletId() {
		return outlet_id;
	}
	
	public String getOutletName() {
		return outlet_name;
	}
	
	public String getOutletStatus() {
		return outlet_status;
	}
	
	public String getOutletImage() {
		return outlet_image;
	}
	
	
	//Setter Methods
	public void setOutletId(int outlet_id) {
		this.outlet_id=outlet_id;
	}
	
	public void setOutletName(String outlet_name) {
		this.outlet_name=outlet_name;
	}
	
	public void setOutletStatus(String outlet_status) {
		this.outlet_status=outlet_status;
	}
	
	public void setOutletImage(String outlet_image) {
		this.outlet_image=outlet_image;
	}
}
