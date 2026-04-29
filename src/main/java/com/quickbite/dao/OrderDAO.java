package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.quickbite.model.CartItemModel;
import com.quickbite.utils.DBconfig;

public class OrderDAO {

	public boolean createOrder (int userId, List<CartItemModel> cart, String instructions) {
		String orderSql = "INSERT INTO order (user_id, order_date, order_status, order_note)"
							+ "VALUES (?, ?, NOW(), 0, ?)";
		String itemSql = "INSERT INTO order_outlet_item (order_id, outlet_id, item_id, item_qty, order_subtotal)"
							+ "VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		try {
			conn = DBconfig.getConnection();
			conn.setAutoCommit(false); // start transaction
			
			// insert parent order
			PreparedStatement ps1 = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, userId);
			ps1.setString(2, instructions);
			ps1.executeUpdate();
			
			// get generated orderID
			ResultSet rs = ps1.getGeneratedKeys();
			int orderId = 0;
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
			
			// insert cart items
			PreparedStatement ps2 = conn.prepareStatement(itemSql);
			for (CartItemModel item : cart) {
				ps2.setInt(1, orderId);
                ps2.setInt(2, item.getOutletId());
                ps2.setInt(3, item.getItemId());
                ps2.setInt(4, item.getQuantity());
                ps2.setDouble(5, item.getTotalPrice());
                ps2.addBatch();
			}
			ps2.executeBatch();
			
			conn.commit();
			return true;
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
