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
	 * @param userId
	 * @throws SQLException
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
