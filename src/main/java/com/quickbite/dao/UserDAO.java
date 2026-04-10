package com.quickbite.dao;

import java.sql.Connection;
import com.quickbite.utils.DBconfig;
import java.sql.PreparedStatement;

public class UserDAO {
	public void insertUser (String fname, String lname, String number, String email, String gender, String dob, String password) throws Exception{
		Connection con = DBconfig.getConnection();
		
		//? marks serves as the placeholders which is later filled using prepared statement below
		String sql = "INSERT INTO users(fname, lname,number, email,gender,dob,password)"+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		//Each setString fills one ? in order
		PreparedStatement pst = con.prepareStatement(sql);
		 pst.setString(1, fname);
	     pst.setString(2, lname);
	     pst.setString(3, number);
	     pst.setString(4, email);
	     pst.setString(5, gender);
	     pst.setString(6, dob);
	     pst.setString(7, password);
	     
	     //This executes the query and saves data to the quickbite database
	     pst.executeUpdate();
	     
	     pst.close();
	     con.close();
	     
	     System.out.println("User inserted into Quickbite DB successfully!");
	}
}
