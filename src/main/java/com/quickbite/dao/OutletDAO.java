package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.Outlet;
import com.quickbite.utils.DBconfig;

public class OutletDAO {
	public List<Outlet> getAllOutlets(){
		List<Outlet> list = new ArrayList<>();
		String query = "Select outlet_id, outlet_name, outlet_status, outlet_image from Outlet";
		
		try(Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
			
			while (rs.next()) {
				list.add(new Outlet(
						rs.getInt("outlet_id"),
						rs.getString("outlet_name"),
						rs.getString("outlet_status"),
						rs.getString("outlet_image")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
