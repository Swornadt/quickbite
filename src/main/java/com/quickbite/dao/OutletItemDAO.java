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

public class OutletItemDAO {

	/**
	 * Retrieves all items for an outlet.
	 * 
	 * Executes a JOIN query linking outlet_items, item, and outlet.
	 * 
	 * @param outletId the unique id for outlet to get the menu for.
	 * @return a List containing populated OutletItemModel entries assigned to the specified outlet
	 */
	public List<OutletItemModel> getItemsByOutlet(int outletId) {
		List<OutletItemModel> outletItems = new ArrayList<>();
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
					ItemModel item = new ItemModel(
							rs.getInt("item_id"),
							rs.getString("item_name"),
							rs.getString("category"),
							rs.getString("item_type"),
							rs.getString("item_description"),
							rs.getString("item_status"),
							rs.getString("item_ingredient"),
							rs.getString("item_allergy"),
							rs.getString("item_image"));
					OutletModel outlet = new OutletModel(
							rs.getInt("outlet_id"),
							rs.getString("outlet_name"),
							rs.getString("outlet_status"),
							rs.getString("outlet_image"));
					OutletItemModel outletItem = new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price"));
					outletItems.add(outletItem);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return outletItems;
	}

	/**
	 * Compiles an inventory of all item-outlet across the entire system.
	 * 
	 * @return a List containing OutletItemModel entity entries sorted alphabetically by item name
	 */
	public List<OutletItemModel> getAllOutletItems() {
		List<OutletItemModel> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, oi.outlet_id " +
				"FROM item i " +
				"JOIN outlet_item oi ON i.item_id = oi.item_id " +
				"ORDER BY i.item_name";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ItemModel item = new ItemModel(
						rs.getInt("item_id"),
						rs.getString("item_name"),
						rs.getString("category"),
						rs.getString("item_type"),
						rs.getString("item_description"),
						rs.getString("item_status"),
						rs.getString("item_ingredient"),
						rs.getString("item_allergy"),
						rs.getString("item_image"));
				OutletModel outlet = new OutletModel(
						rs.getInt("outlet_id"),
						rs.getString("outlet_name"),
						rs.getString("outlet_status"),
						rs.getString("outlet_image"));
				OutletItemModel outletItem = new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price"));
				outletItems.add(outletItem);
			}
		} catch (SQLException e) {
			System.out.println("Error in getAllOutletItems: " + e.getMessage());
			e.printStackTrace();
		}
		return outletItems;
	}

	/**
	 * Adds an item to a specific outlet or modifies its active price if the item for the outlet already exists.
	 * 
	 * @param outletId unique id for outlet where item is to be added or updated.
	 * @param itemId unique id for item to add or update.
	 * @param price the cost value to assign to the item.
	 * @return true if the database transaction successfully registers or modifies the record row; false otherwise
	 */
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

	/**
	 * Audits the outlet to verify if a specific item is currently allocated to the outlet.
	 * 
	 * @param outletId the unique id for outlet for which the item is to be checked.
	 * @param itemId  the unique id for item which is to be checked in the outlet.
	 * @return true if the targeted cross-reference association is explicitly defined within the database; false otherwise
	 */
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

	/**
	 * Resolves an item mapped directly to an explicit outlet.
	 * 
	 * Formulates a JOIN statement using outletId
	 * 
	 * @param itemId the unique id matching the specific food item.
	 * @param outletId the unique id for the outlet.
	 * @return a fully populated OutletItemModel matching the query inputs, or null if no record is discovered
	 */
	public OutletItemModel getOutletItemByItemAndOutlet(int itemId, int outletId) {
		String sql = "SELECT oi.outlet_id, oi.outlet_item_price, " +
				"       i.*, o.outlet_name, o.outlet_status, o.outlet_image " +
				"FROM outlet_item oi " +
				"JOIN item i ON oi.item_id = i.item_id " +
				"JOIN outlet o ON oi.outlet_id = o.outlet_id " +
				"WHERE oi.item_id = ? AND oi.outlet_id = ?";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, itemId);
			ps.setInt(2, outletId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					ItemModel item = new ItemModel(
							rs.getInt("item_id"), rs.getString("item_name"), rs.getString("category"),
							rs.getString("item_type"), rs.getString("item_description"),
							rs.getString("item_status"), rs.getString("item_ingredient"),
							rs.getString("item_allergy"), rs.getString("item_image"));

					OutletModel outlet = new OutletModel(
							rs.getInt("outlet_id"), rs.getString("outlet_name"),
							rs.getString("outlet_status"), rs.getString("outlet_image"));

					return new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Deletes an item in an outlet's database.
	 * 
	 * Prepares a DELETE query for a specific row where oulet_id and item_id matches. 
	 * 
	 * @param outletId unique id for the outlet where item is to be deleted.
	 * @param itemId unique id for the item to be deleted in the given outlet.
	 * @return true if the relationship entry row was successfully dropped from the table index; false otherwise
	 */
	public boolean deleteOutletItem(int outletId, int itemId) {
		String sql = "DELETE FROM outlet_item WHERE outlet_id = ? AND item_id = ?";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, outletId);
			ps.setInt(2, itemId);

			int rows = ps.executeUpdate();
			System.out.println("Deleted " + rows + " row(s) for item " + itemId + " from outlet " + outletId);
			return rows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Filters and compiles menu listings for an outlet matching a category.
	 * 
	 * @param outletId unique id for the outlet where item is to be searched.
	 * @param category the group to filtering the items.
	 * @return a List containing populated OutletItemModel entries that match the branch and category criteria
	 */
	public List<OutletItemModel> getItemsByOutletAndCategory(int outletId, String category) {
		List<OutletItemModel> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, o.outlet_id, o.outlet_name, o.outlet_status, o.outlet_image " +
				"FROM item i " +
				"JOIN outlet_item oi ON i.item_id = oi.item_id " +
				"JOIN outlet o ON oi.outlet_id = o.outlet_id " +
				"WHERE oi.outlet_id = ?";

		if (category != null && !category.equalsIgnoreCase("all")) {
			sql += " and i.category =?";
		}

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, outletId);
			if (category != null && !category.equalsIgnoreCase("all")) {
				ps.setString(2, category);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ItemModel item = new ItemModel(
							rs.getInt("item_id"),
							rs.getString("item_name"),
							rs.getString("category"),
							rs.getString("item_type"),
							rs.getString("item_description"),
							rs.getString("item_status"),
							rs.getString("item_ingredient"),
							rs.getString("item_allergy"),
							rs.getString("item_image"));
					OutletModel outlet = new OutletModel(
							rs.getInt("outlet_id"),
							rs.getString("outlet_name"),
							rs.getString("outlet_status"),
							rs.getString("outlet_image"));
					outletItems.add(new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price")));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outletItems;
	}

	/**
	 * Executes a dynamic keyword text pattern search across an outlet's category.
	 * 
	 * @param outletId  the unique id for the outlet where item is to be searched.
	 * @param category   the category selected by user under which item is to be searched.
	 * @param searchTerm the item searched by the user.
	 * @return a List containing all composite OutletItemModel data records that satisfy the dynamic search filters
	 */
	public List<OutletItemModel> searchItems(int outletId, String category, String searchTerm) {
		List<OutletItemModel> outletItems = new ArrayList<>();
		String sql = "SELECT i.*, oi.outlet_item_price, o.outlet_id, o.outlet_name, o.outlet_status, o.outlet_image " +
				"FROM item i " +
				"JOIN outlet_item oi ON i.item_id = oi.item_id " +
				"JOIN outlet o ON oi.outlet_id = o.outlet_id " +
				"WHERE oi.outlet_id = ?";

		if (category != null && !category.equalsIgnoreCase("all")) {
			sql += " and i.category=?";
		}

		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			sql += " and i.item_name like ?";
		}

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())) {

			int paramIndex = 1;
			ps.setInt(paramIndex++, outletId);

			if (category != null && !category.equalsIgnoreCase("all")) {
				ps.setString(paramIndex++, category);
			}

			if (searchTerm != null && !searchTerm.trim().isEmpty()) {
				ps.setString(paramIndex++, "%" + searchTerm + "%");
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ItemModel item = new ItemModel(
							rs.getInt("item_id"),
							rs.getString("item_name"),
							rs.getString("category"),
							rs.getString("item_type"),
							rs.getString("item_description"),
							rs.getString("item_status"),
							rs.getString("item_ingredient"),
							rs.getString("item_allergy"),
							rs.getString("item_image"));
					OutletModel outlet = new OutletModel(
							rs.getInt("outlet_id"),
							rs.getString("outlet_name"),
							rs.getString("outlet_status"),
							rs.getString("outlet_image"));
					outletItems.add(new OutletItemModel(item, outlet, rs.getDouble("outlet_item_price")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outletItems;
	}
}
