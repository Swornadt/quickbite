package com.quickbite.service;

import com.quickbite.dao.FeedbackDAO;
import com.quickbite.dao.OrderDAO;

public class FeedbackService {

    public void submitFeedback(int orderID, int rating, String message) throws Exception {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        int feedbackId = feedbackDAO.insertFeedback(rating, message);
        
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.updateFeedbackId(orderID, feedbackId);
    }
}