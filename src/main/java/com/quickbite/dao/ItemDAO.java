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
		String sql = "SELECT * FROM items";
		
		try (Connection conn = DBconfig.getConnection();
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ResultSet rs = ps.executeQuery()) {

			 while (rs.next()) {
				 Item item = new Item(         
						 rs.getString("itemName"),
						 rs.getString("category"),
						 rs.getString("itemType"),
						 rs.getString("itemStatus"),
						 rs.getString("itemIngredient"),
						 rs.getString("itemAllergy"),
						 rs.getString("itemImage")
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
