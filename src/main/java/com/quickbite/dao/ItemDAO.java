package com.quickbite.dao;

import java.util.ArrayList;
import java.util.List;
import com.quickbite.model.Item;
import com.quickbite.utils.DBconfig;

import jdk.jfr.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
	
	public List<Item> getAllItems(){
		List<Item> itemList= new ArrayList<>();
		String sql = "SELECT * FROM item";
		
		try (Connection conn = DBconfig.getConnection();
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ResultSet rs = ps.executeQuery()) {

			 while (rs.next()) {
				 	Item item = new Item(
				 			rs.getInt("item_id"),              
				            rs.getString("item_name"),
				            rs.getString("category"),
				            rs.getString("item_type"),
				            rs.getString("item_description"),  
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
	
	public List<String> getCategoriesByOutlet(int outletId){
		List<String> list = new ArrayList<>();
		String query = "Select distinct i.category from item i "+"join outlet_item oi on i.item_id=oi.item_id "+"where oi.outlet_id=?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
						ps.setInt(1, outletId);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						String cat = rs.getString("category");
						if (cat != null) {
							list.add(cat);
						}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
