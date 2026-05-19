package com.quickbite.model;

import java.sql.Timestamp;

/**
 * Represents customer feedback and rating for an order in the Quickbite system.
 */
public class FeedbackModel {
    private int feedbackId;
    private int userId;
    private int ratingValue;
    private String feedbackDescription;
    private Timestamp ratingDate;
    private String userFullName;
    private String userImage;

    /**
     * Constructor for FeedbackModel with essential fields.
     * Automatically sets the rating date to current timestamp.
     *
     * @param userId - the ID of the user giving feedback
     * @param ratingValue - the rating value (e.g., 1 to 5)
     * @param feedbackDescription - the detailed feedback text
     */
    public FeedbackModel(int userId, int ratingValue, String feedbackDescription) {
        this.userId = userId;
        this.ratingValue = ratingValue;
        this.feedbackDescription = feedbackDescription;
        this.ratingDate = Timestamp.valueOf(java.time.LocalDateTime.now());
    }
    
    
    /**
     * Returns the unique identifier of the feedback.
     *
     * @return the feedback ID
     */
	public int getFeedbackId() {
		return feedbackId;
	}
	
	/**
     * Returns the ID of the user who gave the feedback.
     *
     * @return the user ID
     */
	public int getUserId() {
		return userId;
	}
	
	/**
     * Returns the rating value given by the user.
     *
     * @return the rating value
     */
	public int getRatingValue() {
		return ratingValue;
	}
	
	/**
     * Returns the detailed feedback description.
     *
     * @return the feedback description
     */
	public String getFeedbackDescription() {
		return feedbackDescription;
	}
	
	/**
     * Returns the timestamp when the feedback was submitted.
     *
     * @return the rating date
     */
	public Timestamp getRatingDate() {
		return ratingDate;
	}
	
	/**
     * Returns the full name of the user who gave feedback.
     * Returns "Unknown User" if name is not available.
     *
     * @return the user full name
     */
	public String getUserFullName() {
		return userFullName != null ? userFullName : "Unknown User";
	}
	
	/**
     * Returns the profile image of the user.
     * Returns default image path if no image is set.
     *
     * @return the user image filename
     */
	public String getUserImage() {
		return userImage != null ? userImage : "uploads/default.png";
	}
	
	/**
     * Sets the unique identifier of the feedback.
     *
     * @param feedbackId - the feedback ID to set
     */
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	/**
     * Sets the ID of the user who gave the feedback.
     *
     * @param userId - the user ID to set
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
     * Sets the rating value.
     *
     * @param ratingValue - the rating value to set
     */
	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}
	
	/**
     * Sets the feedback description.
     *
     * @param feedbackDescription - the feedback description to set
     */
	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
	
	/**
     * Sets the timestamp of the feedback.
     *
     * @param ratingDate - the rating date to set
     */
	public void setRatingDate(Timestamp ratingDate) {
		this.ratingDate = ratingDate;
	}
	
	/**
     * Sets the full name of the user.
     *
     * @param userFullName - the user full name to set
     */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	/**
     * Sets the profile image of the user.
     *
     * @param userImage - the user image filename to set
     */
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
}
