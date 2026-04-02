package com.quickbite.dao;

import java.util.ArrayList;
import java.util.List;
import com.quickbite.model.Item;
import com.quickbite.utils.DBconfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
	
	public List<Item> getAllItems(){
		List<Item> itemList= new ArrayList<>();
		String sql = "SELECT * FROM Item";
		
		try (Connection conn = DBconfig.getConnection();
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ResultSet rs = ps.executeQuery()) {

			 while (rs.next()) {
				 Item item = new Item(         
						 rs.getString("item_name"),
						 rs.getString("category"),
						 rs.getString("item_type"),
						 rs.getString("item_status"),
						 rs.getString("item_ingredient"),
						 rs.getString("item_allergy"),
						 rs.getString("item_image")
					);
		    	  
			            itemList.add(item);           
			 }

		 } 
		 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 
		return itemList;
	}
}
