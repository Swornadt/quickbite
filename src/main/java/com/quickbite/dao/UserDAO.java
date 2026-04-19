package com.quickbite.dao;

import java.sql.Connection;

import com.quickbite.utils.DBconfig;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quickbite.model.UserModel;

public class UserDAO {
	
	public void insertUser (String fname, String lname, String number, String email, String gender, String dob, String password, String image) throws Exception{
		Connection con = DBconfig.getConnection();
		
		//? marks serves as the placeholders which is later filled using prepared statement below
		String sql = "INSERT INTO user(fname, lname, number, email, gender, dob, password, role, status, image)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		//Each setString fills one ? in order
		PreparedStatement pst = con.prepareStatement(sql);
		 pst.setString(1, fname);
	     pst.setString(2, lname);
	     pst.setString(3, number);
	     pst.setString(4, email);
	     pst.setString(5, gender);
	     pst.setString(6, dob);
	     pst.setString(7, password);
	     pst.setString(8, "customer");
	     pst.setString(9, "pending");
	     pst.setString(10, image);
	     //This executes the query and saves data to the quickbite database
	     pst.executeUpdate();
	     
	     pst.close();
	     con.close();
	     
	     System.out.println("User inserted into Quickbite DB successfully!");
	}
	
	public UserModel getUserByNumber(String number) {
		String sql = "SELECT * FROM user WHERE number = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql)) {
			
			pst.setString(1, number);
			
			try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    
                    user.setUserId(rs.getInt("user_id"));
                    user.setFname(rs.getString("fname"));
                    user.setLname(rs.getString("lname"));
                    user.setNumber(rs.getString("number"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));
                    user.setDob(rs.getString("dob"));
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
