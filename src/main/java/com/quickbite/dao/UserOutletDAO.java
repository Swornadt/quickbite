package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quickbite.model.UserModel;
import com.quickbite.model.OutletModel;
import com.quickbite.utils.DBconfig;

public class UserOutletDAO {
	
	public int getOutletByUser(int userID) {
		String sql = "SELECT outlet_id FROM user_outlet WHERE user_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, userID);
	        
			try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("outlet_id");
					}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
