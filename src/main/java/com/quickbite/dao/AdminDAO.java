package com.quickbite.dao;

import java.sql.Connection;

import com.quickbite.utils.DBconfig;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.quickbite.model.AdminModel;

public class AdminDAO {	
	public AdminModel getAdminById(int user_id) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql)) {
			
			pst.setInt(1, user_id);
			
			try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    AdminModel user = new AdminModel();
                    
                    user.setUserId(rs.getInt("user_id"));
                    user.setFname(rs.getString("fname"));
                    user.setLname(rs.getString("lname"));
                    user.setNumber(rs.getString("number"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));
                    user.setDob(rs.getDate("dob"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
		} catch (SQLException e) {
			System.err.println("Error fetching user: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
				
				
	}
}
