package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.FeedbackModel;
import com.quickbite.utils.DBconfig;

public class FeedbackDAO {

    public int insertFeedback(int rating, String message) throws Exception {
        String sql = "INSERT INTO feedback (rating_value, feedback_description, rating_date) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, rating);
            pst.setString(2, message);
            pst.setTimestamp(3, Timestamp.valueOf(java.time.LocalDateTime.now()));

            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
            	return rs.getInt(1);
            }
        }
		return -1;
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