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
	
	
	// Check if item with same name already exists
	public Item getItemByName(String itemName) {
	    String sql = "SELECT * FROM item WHERE item_name = ?";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, itemName.trim());
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            return new Item(
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
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	// Add new item and return generated ID
	public int addItemAndReturnId(Item item) {
	    String sql = "INSERT INTO item (item_name, category, item_type, item_description, "
	               + "item_status, item_ingredient, item_allergy, item_image) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
	        
	        ps.setString(1, item.getItemName());
	        ps.setString(2, item.getCategory());
	        ps.setString(3, item.getItemType());
	        ps.setString(4, item.getItemDescription());
	        ps.setString(5, item.getItemStatus());
	        ps.setString(6, item.getItemIngredient());
	        ps.setString(7, item.getItemAllergy());
	        ps.setString(8, item.getItemImage());

	        int affectedRows = ps.executeUpdate();
	        
	        if (affectedRows > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    return rs.getInt(1);   // Return generated item_id
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
    
    
	public boolean addItem(Item item) {
        int id = addItemAndReturnId(item);
        return id > 0;
    }
	
	
    
    // Update existing item
    public boolean updateItem(Item item) {
        String sql = "UPDATE Item SET item_name=?, category=?, item_type=?, item_description=?, item_status=?, item_ingredient=?, item_allergy=?, item_image=?, price=?, outlet_id=? WHERE item_id=?";
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getCategory());
            ps.setString(3, item.getItemType());
            ps.setString(4, item.getItemDescription());
            ps.setString(5, item.getItemStatus());
            ps.setString(6, item.getItemIngredient());
            ps.setString(7, item.getItemAllergy());
            ps.setString(8, item.getItemImage());
            ps.setInt(11, item.getItemId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    // Delete an item
    public boolean deleteItem(int itemId) {
        String sql = "DELETE FROM Item WHERE item_id = ?";
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
