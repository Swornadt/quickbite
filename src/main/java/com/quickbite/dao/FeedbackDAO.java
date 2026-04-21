package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
}