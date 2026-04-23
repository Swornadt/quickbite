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
						 rs.getString("item_image"),
						 rs.getDouble("outlet_item_price")
					);
		    	  
			            itemList.add(item);           
			 }

		 } 
		 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 
		return itemList;
	}
	public List<Item> getItemsByOutlet(int outletId){
		List<Item> itemList=new ArrayList<>();
		String sql= "select i.*, oi.outlet_item_price " +
                "from Item i " +
                "join outlet_item oi ON i.item_id = oi.item_id " +
                "where oi.outlet_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, outletId);
			
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					Item item = new Item(
							rs.getString("item_name"),
	                        rs.getString("category"),
	                        rs.getString("item_type"),
	                        rs.getString("item_status"),
	                        rs.getString("item_ingredient"),
	                        rs.getString("item_allergy"),
	                        rs.getString("item_image"),
	                        rs.getDouble("outlet_item_price"));
					itemList.add(item);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());		}
		return itemList;
	}
}
