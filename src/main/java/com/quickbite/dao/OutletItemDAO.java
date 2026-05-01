package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.Item;
import com.quickbite.model.OutletItem;
import com.quickbite.utils.DBconfig;

public class OutletItemDAO {

	public List<OutletItem> getItemsByOutlet(int outletId){
		List<OutletItem> outletItems=new ArrayList<>();
		String sql= "select i.*, oi.outlet_item_price " +
                "from item i " +
                "join outlet_item oi ON i.item_id = oi.item_id " +
                "where oi.outlet_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, outletId);
			
			try(ResultSet rs= ps.executeQuery()){
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
					OutletItem outletItem = new OutletItem(item, rs.getDouble("outlet_item_price"));
					outletItems.add(outletItem);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());		}
		return outletItems;
	}
	
	// Add or Update price for an item in a specific outlet
	public boolean addOrUpdateOutletItem(int outletId, int itemId, double price) {

	    String checkSql = "SELECT outlet_item_price FROM outlet_item WHERE outlet_id = ? AND item_id = ?";
	    
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
	        
	        checkPs.setInt(1, outletId);
	        checkPs.setInt(2, itemId);
	        
	        try (ResultSet rs = checkPs.executeQuery()) {
	            if (rs.next()) {
	                
	                String updateSql = "UPDATE outlet_item SET outlet_item_price = ? WHERE outlet_id = ? AND item_id = ?";
	                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
	                    updatePs.setDouble(1, price);
	                    updatePs.setInt(2, outletId);
	                    updatePs.setInt(3, itemId);
	                    int rows = updatePs.executeUpdate();
	                    return rows > 0;
	                }
	            } else {
	                String insertSql = "INSERT INTO outlet_item (outlet_id, item_id, outlet_item_price) VALUES (?, ?, ?)";
	                try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
	                    insertPs.setInt(1, outletId);
	                    insertPs.setInt(2, itemId);
	                    insertPs.setDouble(3, price);
	                    int rows = insertPs.executeUpdate();
	                    return rows > 0;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
