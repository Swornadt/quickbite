package com.quickbite.model;

public class OutletModel {
	private int outletId;
	private String outletName;
	private String outletStatus;
	private String outletImage;

	//Constructors
	/**
     * Constructor of OutletModel with all fields initialized.
     *
     * @param outletId - the unique identifier of the outlet
     * @param outletName - the name of the outlet
     * @param outletStatus - the current status of the outlet
     * @param outletImage - the image  associated with the outlet
     */
	public OutletModel(int outletId, String outletName, String outletStatus, String outletImage) {
		this.outletId=outletId;
		this.outletName=outletName;
		this.outletStatus=outletStatus;
		this.outletImage=outletImage;
	}
	
	/**
     * Returns the unique identifier of the outlet.
     *
     * @return the outlet ID
     */
	public int getOutletId() {
		return outletId;
	}
	
	/**
     * Returns the name of the outlet.
     *
     * @return the outlet name
     */
	public String getOutletName() {
		return outletName;
	}
	
	/**
     * Returns the current status of the outlet.
     *
     * @return the outlet status
     */
	public String getOutletStatus() {
		return outletStatus;
	}
	
	/**
     * Returns the image filename associated with the outlet.
     *
     * @return the outlet image filename
     */
	public String getOutletImage() {
		return outletImage;
	}
		
	//Setter Methods
	/**
     * Sets the unique identifier of the outlet.
     *
     * @param outletId the outlet ID to set
     */
	public void setOutletId(int outletId) {
		this.outletId=outletId;
	}
	
	/**
     * Sets the name of the outlet.
     *
     * @param outletName the outlet name to set
     */
	public void setOutletName(String outletName) {
		this.outletName=outletName;
	}
	
	/**
     * Sets the current status of the outlet.
     *
     * @param outletStatus the outlet status to set
     */
	public void setOutletStatus(String outletStatus) {
		this.outletStatus=outletStatus;
	}
	
	/**
     * Sets the image filename associated with the outlet.
     *
     * @param outletImage the outlet image filename to set
     */
	public void setOutletImage(String outletImage) {
		this.outletImage=outletImage;
	}
}
