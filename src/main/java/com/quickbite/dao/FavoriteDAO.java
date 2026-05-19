package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.ItemModel;
import com.quickbite.model.OutletModel;
import com.quickbite.model.OutletItemModel;
import com.quickbite.utils.DBconfig;

public class FavoriteDAO {
	
	/**
	 * Inserts a new favorite item record into the database for a specific user and outlet combination.
	 * 
	 * Establishes a database connection via DBconfig and prepares an INSERT SQL statement to safely
	 * log the relationship between a user, a specific menu item, and its outlet. 
	 * It binds the parameters to prevent SQL injection and executes a data update.
	 * 
	 * @param userId the unique id for the customer.
	 * @param itemId the unique id representing the specific bookmarked food item.
	 * @param outletId the unique id representing the targeted outlet.
	 * @throws SQLException if a core database access conflict or connectivity drop breaks statement execution.
	 */
	public void addFavorite (int userId, int itemId, int outletId) throws SQLException {
		String sql = "INSERT INTO favorite (user_id, item_id, outlet_id) "
                + "VALUES (?, ?, ?)";

     try (Connection con = DBconfig.getConnection();
          PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setInt(1, userId);
         pst.setInt(2, itemId);
         pst.setInt(3, outletId);

         pst.executeUpdate();
     	}
	}
	
	/**
	 * Deletes an existing favorite item record from the database.
	 * 
	 * Establishes a database connection via DBconfig and prepares a DELETE SQL statement to remove 
	 * the specific menu item, user and outlet relationship from the database.
	 * 
	 * @param userId the unique id for the customer.
	 * @param itemId the unique id representing the specific food item being unbookmarked.
	 * @param outletId the unique id representing the targeted outlet.
	 * @throws SQLException if a database query execution error or connection failure occurs.
	 */
	public void removeFavorite (int userId, int itemId, int outletId) throws SQLException {
		String sql = "DELETE FROM favorite WHERE user_id = ? AND item_id = ? AND outlet_id = ? ";

     try (Connection con = DBconfig.getConnection();
          PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setInt(1, userId);
         pst.setInt(2, itemId);
         pst.setInt(3, outletId);

         pst.executeUpdate();
     	}
	}
	
	/**
	 * Checks whether a specific menu item from a given outlet is favorited by a user.
	 * 
	 * Prepares a SELECT query to check for the existence of an active relational 
	 * record matching the provided user, item, and outlet criteria. 
	 * Returns true if the query yields a matching row result, otherwise returns false.
	 * 
	 * @param userId the unique id of the customer.
	 * @param itemId the unique id representing the item.
	 * @param outletId the unique id representing the targeted outlet.
	 * @return true if a matching preference relationship is found; false otherwise.
	 * @throws SQLException if a relational scanning failure or database connection error pops up.
	 */
	public boolean isFavorite (int userId, int itemId, int outletId) throws SQLException {
		String sql = "SELECT 1 FROM favorite WHERE user_id = ? AND item_id = ? AND outlet_id = ?";
		
		try (Connection con = DBconfig.getConnection();
		          PreparedStatement pst = con.prepareStatement(sql)) {

		         pst.setInt(1, userId);
		         pst.setInt(2, itemId);
		         pst.setInt(3, outletId);

		         try (ResultSet rs = pst.executeQuery()) {
		        	 return rs.next();
		         }
		}
	}
	
	/**
	 * Fetches all favorite items for a user.
	 * 
	 * Joins with Item and Outlet_Item to get current price and details
	 * @param userId unique id for user to search the favorite items.
	 * @throws SQLException if a data pipeline failure or bad join statement disrupts row extraction.
	 */
	public List<OutletItemModel> getFavoritesByUser(int userId) throws SQLException {
		List<OutletItemModel> favoriteItems = new ArrayList<>();
		String sql = "SELECT i.*, o.*, oi.outlet_item_price FROM favorite f " +
						"JOIN item i ON f.item_id = i.item_id " +
						"JOIN outlet_item oi ON f.item_id = oi.item_id AND f.outlet_id = oi.outlet_id " +
						"JOIN outlet o ON f.outlet_id = o.outlet_id " +
						"WHERE f.user_id = ?";

	     try (Connection con = DBconfig.getConnection();
	          PreparedStatement pst = con.prepareStatement(sql)) {
	
	         	pst.setInt(1, userId);
	         
	         	try (ResultSet rs = pst.executeQuery()) {
	                while (rs.next()) {
	                    // map the general item
	                	ItemModel item = new ItemModel();
	                	item.setItemId(rs.getInt("item_id"));
	                    item.setItemName(rs.getString("item_name"));
	                    // map the outlet
	                    OutletModel outlet = new OutletModel(
	                    		rs.getInt("outlet_id"),
	                    		rs.getString("outlet_name"), 
	                    		null, 
	                    		null
	                    );
	                    // combining into outletItem
	                    OutletItemModel favRecord = new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price"));
	                    
	                    favoriteItems.add(favRecord);
	                }
	         	}
	     }
	    return favoriteItems;
	}	
}
