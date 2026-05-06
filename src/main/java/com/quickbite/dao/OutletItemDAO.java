package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.Item;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;
import com.quickbite.utils.DBconfig;

public class OutletItemDAO {

	public List<OutletItem> getItemsByOutlet(int outletId) {
		List<OutletItem> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, o.outlet_id, o.outlet_name, o.outlet_status, o.outlet_image " +
	             "FROM item i " +
	             "JOIN outlet_item oi ON i.item_id = oi.item_id " +
	             "JOIN outlet o ON oi.outlet_id = o.outlet_id " +
	             "WHERE oi.outlet_id = ?";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, outletId);

			try (ResultSet rs = ps.executeQuery()) {
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
					Outlet outlet = new Outlet(
							rs.getInt("outlet_id"), 
							rs.getString("outlet_name"), 
							rs.getString("outlet_status"),
							rs.getString("outlet_image")
						);
					OutletItem outletItem = new OutletItem(item, outlet, rs.getDouble("outlet_item_price"));
					outletItems.add(outletItem);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return outletItems;
	}

	public List<OutletItem> getAllOutletItems() {
		List<OutletItem> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, oi.outlet_id " +
				"FROM item i " +
				"JOIN outlet_item oi ON i.item_id = oi.item_id " +
				"ORDER BY i.item_name";

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
				Outlet outlet = new Outlet(
						rs.getInt("outlet_id"), 
						rs.getString("outlet_name"), 
						rs.getString("outlet_status"),
						rs.getString("outlet_image")
					);
				OutletItem outletItem = new OutletItem(item, outlet, rs.getDouble("outlet_item_price"));
				outletItems.add(outletItem);
			}
		} catch (SQLException e) {
			System.out.println("Error in getAllOutletItems: " + e.getMessage());
			e.printStackTrace();
		}
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

	public boolean isItemExistsInOutlet(int outletId, int itemId) {
		String sql = "SELECT COUNT(*) FROM outlet_item WHERE outlet_id = ? AND item_id = ?";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, outletId);
			ps.setInt(2, itemId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete all outlet_item entries for a specific item (required before deleting
	// item)
	public boolean deleteOutletItemsByItemId(int itemId) {
		String sql = "DELETE FROM outlet_item WHERE item_id = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, itemId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<OutletItem> getItemsByOutletAndCategory(int outletId, String category){
		List <OutletItem> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, o.outlet_id, o.outlet_name, o.outlet_status, o.outlet_image " +
	             "FROM item i " +
	             "JOIN outlet_item oi ON i.item_id = oi.item_id " +
	             "JOIN outlet o ON oi.outlet_id = o.outlet_id " +
	             "WHERE oi.outlet_id = ?";
		
		if(category !=null && !category.equalsIgnoreCase("all")) {
			sql += " and i.category =?";
		}
		
		try(Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, outletId);
			if(category != null && !category.equalsIgnoreCase("all")) {
				ps.setString(2, category);
			}
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
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
					Outlet outlet = new Outlet(
							rs.getInt("outlet_id"), 
							rs.getString("outlet_name"), 
							rs.getString("outlet_status"),
							rs.getString("outlet_image")
						);
					outletItems.add(new OutletItem(item, outlet, rs.getDouble("outlet_item_price")));
							
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return outletItems;
	}
	
	public List<OutletItem> searchItems(int outletId, String category, String searchTerm){
		List<OutletItem> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, o.outlet_id, o.outlet_name, o.outlet_status, o.outlet_image " +
	             "FROM item i " +
	             "JOIN outlet_item oi ON i.item_id = oi.item_id " +
	             "JOIN outlet o ON oi.outlet_id = o.outlet_id " +
	             "WHERE oi.outlet_id = ?";
		
		if(category != null && !category.equalsIgnoreCase("all")) {
			sql += " and i.category=?";
		}
		
		if(searchTerm != null && !searchTerm.trim().isEmpty()) {
			sql += " and i.item_name like ?";
		}
		
		try(Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			int paramIndex=1;
			ps.setInt(paramIndex++, outletId);
			
			if(category != null && !category.equalsIgnoreCase("all")) {
				ps.setString(paramIndex++, category);
			}
			
			if(searchTerm != null && !searchTerm.trim().isEmpty()) {
				ps.setString(paramIndex++, "%" + searchTerm + "%");
			}
			
			try(ResultSet rs = ps.executeQuery()){
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
					Outlet outlet = new Outlet(
							rs.getInt("outlet_id"), 
							rs.getString("outlet_name"), 
							rs.getString("outlet_status"),
							rs.getString("outlet_image")
						);
					outletItems.add(new OutletItem(item, outlet, rs.getDouble("outlet_item_price")));
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return outletItems;
	}
}
