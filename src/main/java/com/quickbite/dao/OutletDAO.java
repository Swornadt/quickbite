package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.OutletModel;
import com.quickbite.utils.DBconfig;

public class OutletDAO {
	
	/**
	 * Retrieves all outlets in the database.
	 * 
	 * Executes a query against the outlet table to fetch fields.
	 * @return a List containing complete OutletModel data mappings for every registered branch outlet
	 */
	public List<OutletModel> getAllOutlets() {
		List<OutletModel> list = new ArrayList<>();
		String query = "Select outlet_id, outlet_name, outlet_status, outlet_image from outlet";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new OutletModel(
						rs.getInt("outlet_id"),
						rs.getString("outlet_name"),
						rs.getString("outlet_status"),
						rs.getString("outlet_image")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Finds and returns a specific outlet matching the provided outletName.
	 * 
	 * Prepares a conditional query for outletName. 
	 * 
	 * @param outletName the name of the outlet to search.
	 * @return an OutletModel mapping containing the target outlet details, or null if no record matches
	 */
	public OutletModel getOutletByName(String outletName) {
		String sql = "SELECT * FROM outlet WHERE outlet_name = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, outletName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new OutletModel(
						rs.getInt("outlet_id"),
						rs.getString("outlet_name"),
						rs.getString("outlet_status"),
						rs.getString("outlet_image"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Retrieves a specific outlet by outletId.
	 * 
	 * @param outletId the id of the outlet to search.
	 * @return an OutletModel mapping containing the target outlet details, or null if no record matches
	 */
	public OutletModel getOutletById(int outletId) {
		String sql = "SELECT * FROM outlet WHERE outlet_id = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, outletId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new OutletModel(
						rs.getInt("outlet_id"),
						rs.getString("outlet_name"),
						rs.getString("outlet_status"),
						rs.getString("outlet_image"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
