package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.Outlet;
import com.quickbite.utils.DBconfig;

public class OutletDAO {
	public List<Outlet> getAllOutlets() {
		List<Outlet> list = new ArrayList<>();
		String query = "Select outlet_id, outlet_name, outlet_status, outlet_image from outlet";

		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new Outlet(
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

	public Outlet getOutletByName(String outletName) {
		String sql = "SELECT * FROM outlet WHERE outlet_name = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, outletName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Outlet(
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

	public Outlet getOutletById(int outletId) {
		String sql = "SELECT * FROM outlet WHERE outlet_id = ?";
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, outletId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Outlet(
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
