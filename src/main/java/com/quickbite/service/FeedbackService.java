package com.quickbite.service;

import com.quickbite.dao.FeedbackDAO;

public class FeedbackService {

    public void submitFeedback(int userId, int rating, String message) throws Exception {
        FeedbackDAO dao = new FeedbackDAO();
        dao.insertFeedback(userId, rating, message);
    }
}