package com.quickbite.model;

import java.sql.Timestamp;

public class FeedbackModel {
    private int feedbackId;
    private int userId;
    private int ratingValue;
    private String feedbackDescription;
    private Timestamp ratingDate;

    // Constructor, getters and setters...
    public FeedbackModel(int userId, int ratingValue, String feedbackDescription) {
        this.userId = userId;
        this.ratingValue = ratingValue;
        this.feedbackDescription = feedbackDescription;
        this.ratingDate = Timestamp.valueOf(java.time.LocalDateTime.now());
    }
	
	//Getters
	public int getFeedbackId() {
		return feedbackId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getRatingValue() {
		return ratingValue;
	}
	
	public String getFeedbackDescription() {
		return feedbackDescription;
	}
	
	public Timestamp getRatingDate() {
		return ratingDate;
	}
	
	// Setters
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}
	
	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
	
	public void setRatingDate(Timestamp ratingDate) {
		this.ratingDate = ratingDate;
	}
}
