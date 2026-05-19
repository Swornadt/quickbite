package com.quickbite.service;

import com.quickbite.dao.FeedbackDAO;
import com.quickbite.dao.OrderDAO;

public class FeedbackService {

	/**
	 * It submits a feedback for a given orderID, storing the respective rating and message.
	 * 
	 * It instantiates a FeedbackDAO to insert new score rating and review into the database.
	 * Once the new feedback is recorded, it accesses the OrderDAO to attach that feedback id
	 * to the respective orderID
	 * 
	 * @param orderID the primary database identifier key matching the completed transaction.
     * @param rating the numeric score scale assigned by the customer to the experience.
     * @param message the text string containing the customer's review.
     * @throws Exception if a database connectivity error or query breakdown stops either record update.
	 */
    public void submitFeedback(int orderID, int rating, String message) throws Exception {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        int feedbackId = feedbackDAO.insertFeedback(rating, message);
        
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.updateFeedbackId(orderID, feedbackId);
    }
}