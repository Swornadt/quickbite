package com.quickbite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quickbite.model.ItemModel;
import com.quickbite.utils.DBconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {

	/**
	 * Retrieves all items in the database.
	 * 
	 * @return a List containing complete ItemModel data mappings for every item row record
	 */
	public List<ItemModel> getAllItems() {
		List<ItemModel> itemList = new ArrayList<>();
		String sql = "SELECT * FROM item";

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
				itemList.add(item);
			}

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return itemList;
	}

	/**
	 * Finds and returns a specific item record matching the provided itemName.
	 * 
	 * Prepares a conditional query targeting the item name. 
	 * The method strips leading and trailing whitespace from the target parameter input to prevent evaluation gaps, 
	 * mapping the matching table details into a clean ItemModel state object. 
	 * Returns null if no matching row record is discovered inside the database tracking index.
	 * 
	 * @param itemName
	 * @return an ItemModel mapping containing the target item details, or null if no record matches
	 */
	public ItemModel getItemByName(String itemName) {
		String sql = "SELECT * FROM item WHERE item_name = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, itemName.trim());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new ItemModel(
						rs.getInt("item_id"),
						rs.getString("item_name"),
						rs.getString("category"),
						rs.getString("item_type"),
						rs.getString("item_description"),
						rs.getString("item_status"),
						rs.getString("item_ingredient"),
						rs.getString("item_allergy"),
						rs.getString("item_image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Compiles a collection of popular menu items with their outlet.
	 * 
	 * Executes a JOIN query linking outlet, outlet_item, and item.
	 * 
	 * @return a List containing Map entries that pair ItemModel with their respective outlet
	 */
	public List<Map<String, Object>> getPopularItemsWithOutlets() {
		List<Map<String, Object>> popularList = new ArrayList<>();
		String sql = "SELECT i.*, o.outlet_name FROM outlet_item oi " +
	             "JOIN item i ON oi.item_id = i.item_id " +
	             "JOIN outlet o ON oi.outlet_id = o.outlet_id " +
	             "WHERE (oi.outlet_id, oi.item_id) IN (" +
	             "    SELECT outlet_id, MIN(item_id) " +
	             "    FROM outlet_item " +
	             "    GROUP BY outlet_id" +
	             ") LIMIT 6";

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
						rs.getString("item_image")
				);
				
				Map<String, Object> dataMap = new HashMap<>();
	            dataMap.put("itemDetails", item);
	            dataMap.put("outletName", rs.getString("outlet_name"));

				popularList.add(dataMap);
			}

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return popularList;
	}

	/**
	 * Adds a new record to the database and returns its auto-assigned primary key.
	 * 
	 * Establishes a database connection and sets up an INSERT query.
	 * It instructs the database driver to return generated keys during execution, 
	 * capturing the assigned unique row index field immediately upon a successful write 
	 * transaction.
	 * 
	 * @param item
	 * @return the unique generated integer key of the new item record, or -1 if the write transaction fails
	 */
	public int addItemAndReturnId(ItemModel item) {
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
						return rs.getInt(1); // Return generated item_id
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Persists a new item record to the database and evaluates the completion status as a confirmation flag.
	 * 
	 * @param item
	 * @return true if the item was successfully written to the database; false otherwise
	 */
	public boolean addItem(ItemModel item) {
		int id = addItemAndReturnId(item);
		return id > 0;
	}

	/**
	 * Retrieves a specific menu item configuration matching the provided itemId.
	 * 
	 * Prepares a query targeting the primary itemId. 
	 * Returns null if the specified key context does not exist in the system.
	 * 
	 * @param itemId
	 * @return an ItemModel mapping containing the target item details, or null if no record matches
	 */
	public ItemModel getItemById(int itemId) {
		String sql = "SELECT * FROM item WHERE item_id = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, itemId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new ItemModel(
							rs.getInt("item_id"),
							rs.getString("item_name"),
							rs.getString("category"),
							rs.getString("item_type"),
							rs.getString("item_description"),
							rs.getString("item_status"),
							rs.getString("item_ingredient"),
							rs.getString("item_allergy"),
							rs.getString("item_image"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Modifies an existing item's attribute in the database.
	 * 
	 * Prepares an UPDATE statement modifying descriptions, images, status, ingredients, and allergy. 
	 * 
	 * @param item 
	 * @return true if at least one matching database record row was successfully modified; false otherwise
	 */
	public boolean updateItem(ItemModel item) {
		String sql = "UPDATE item SET item_name=?, category=?, item_type=?, item_description=?, "
				+ "item_status=?, item_ingredient=?, item_allergy=?, item_image=? "
				+ "WHERE item_id=?";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, item.getItemName());
			ps.setString(2, item.getCategory());
			ps.setString(3, item.getItemType());
			ps.setString(4, item.getItemDescription() != null ? item.getItemDescription() : "");
			ps.setString(5, item.getItemStatus() != null ? item.getItemStatus() : "Available");
			ps.setString(6, item.getItemIngredient() != null ? item.getItemIngredient() : "");
			ps.setString(7, item.getItemAllergy() != null ? item.getItemAllergy() : "");
			ps.setString(8, item.getItemImage() != null ? item.getItemImage() : "");
			ps.setInt(9, item.getItemId());

			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a target item from the database.
	 * 
	 * Formulates a DELETE statement targeting the specific row linked to the itemId. 
	 * 
	 * @param itemId
	 * @return true if the matching record was successfully removed from the persistence store; false otherwise
	 */
	public boolean deleteItem(int itemId) {
		String sql = "DELETE FROM Item WHERE item_id = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
