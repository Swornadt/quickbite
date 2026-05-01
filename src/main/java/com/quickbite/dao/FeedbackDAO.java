package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.FeedbackModel;
import com.quickbite.utils.DBconfig;

public class FeedbackDAO {

    public void insertFeedback(int userId, int rating, String message) throws Exception {
        String sql = "INSERT INTO feedback (user_id, rating_value, feedback_description, rating_date) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, userId);
            pst.setInt(2, rating);
            pst.setString(3, message);
            pst.setTimestamp(4, Timestamp.valueOf(java.time.LocalDateTime.now()));

            pst.executeUpdate();
            System.out.println("Feedback inserted successfully for user_id: " + userId);
        }
    }
    
    public List<FeedbackModel> getAllFeedbacks() throws Exception {
        List<FeedbackModel> list = new ArrayList<>();
        
        String sql = "SELECT f.*, CONCAT(u.fname, ' ', u.lname) AS userFullName " +
                     "FROM feedback f " +
                     "JOIN user u ON f.user_id = u.user_id " +
                     "ORDER BY f.rating_date DESC";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                FeedbackModel fb = new FeedbackModel();
                fb.setFeedbackId(rs.getInt("feedback_id"));
                fb.setUserId(rs.getInt("user_id"));
                fb.setRatingValue(rs.getInt("rating_value"));
                fb.setFeedbackDescription(rs.getString("feedback_description"));
                fb.setRatingDate(rs.getTimestamp("rating_date"));
                fb.setUserFullName(rs.getString("userFullName"));
                
                list.add(fb);
            }
        }
        return list;
    }
}