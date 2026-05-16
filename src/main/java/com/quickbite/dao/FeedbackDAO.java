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

	/**
	 * Inserts a new customer feedback record into the database and retrieves its generated primary key.
	 * 
	 * Establishes a database connection via DBconfig and prepares an INSERT SQL statement to commit 
	 * the rating score evaluation, descriptive message text, and the current system timestamp. 
	 * 
	 * @param rating
	 * @param message
	 * @return the unique generated key of the new feedback record, or -1 if insertion fails
	 * @throws Exception 
	 */
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
    
    /**
	 * Retrieves and maps a complete list of all customer feedback submissions sorted by entry date.
	 * 
	 * Executes a LEFT JOIN query linking the feedback table with order and user profile accounts. 
	 * 
	 * @return a List containing complete FeedbackModel data sorted in descending order by submission date
	 * @throws Exception
	 */
    public List<FeedbackModel> getAllFeedbacks() throws Exception {
        List<FeedbackModel> list = new ArrayList<>();
        
        String sql = "SELECT " +
                     "    f.feedback_id, " +
                     "    f.rating_value, " +
                     "    f.feedback_description, " +
                     "    f.rating_date, " +
                     "    CONCAT(u.fname, ' ', u.lname) AS userFullName, " +
                     "    COALESCE(u.image, 'uploads/default.png') AS userImage " +
                     "FROM feedback f " +
                     "LEFT JOIN `order` o ON f.feedback_id = o.feedback_id " +
                     "LEFT JOIN user u ON o.user_id = u.user_id " +
                     "ORDER BY f.rating_date DESC";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                FeedbackModel fb = new FeedbackModel();
                
                fb.setFeedbackId(rs.getInt("feedback_id"));
                fb.setRatingValue(rs.getInt("rating_value"));
                fb.setFeedbackDescription(rs.getString("feedback_description"));
                fb.setRatingDate(rs.getTimestamp("rating_date"));
                fb.setUserFullName(rs.getString("userFullName"));
                fb.setUserImage(rs.getString("userImage"));
                
                list.add(fb);
            }

        } catch (Exception e) {
            System.err.println("ERROR in getAllFeedbacks(): " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}