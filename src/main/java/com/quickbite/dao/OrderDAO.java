package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.CartItemModel;
import com.quickbite.model.OrderModel;
import com.quickbite.utils.DBconfig;

public class OrderDAO {

	public int createOrder (int userId, List<CartItemModel> cart, String instructions, String preferredDate, int paymentId) {
		String orderSql = "INSERT INTO `order` (user_id, order_date, order_status, order_note, preferred_date, payment_id)"
                		+ " VALUES (?, NOW(), 0, ?, ?, ?)";
		String itemSql = "INSERT INTO order_outlet_item (order_id, outlet_id, item_id, item_qty, order_subtotal)"
		                + " VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
		    conn = DBconfig.getConnection();
		    conn.setAutoCommit(false);
		
		    PreparedStatement ps1 = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
		    ps1.setInt(1, userId);
		    ps1.setString(2, instructions);
		    ps1.setString(3, preferredDate);
		    ps1.setInt(4, paymentId);
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
			return orderId;

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<OrderModel> getOrdersByStatus(int userId, boolean isCurrent) {
		List<OrderModel> orders = new ArrayList<>();
		
		// 0: pending; 1: processing; 2: completed"
		String statusCondition = isCurrent ? "IN (0, 1)" : "= 2";
		
		String query = "SELECT order_id, order_date, order_status, order_note, feedback_id FROM `order` "+
						" WHERE user_id = ? AND order_status "+ statusCondition +
						" ORDER BY order_date DESC";
		
		try (Connection conn = DBconfig.getConnection();
			PreparedStatement pre = conn.prepareStatement(query)) {
			
			pre.setInt(1, userId);
			ResultSet rs = pre.executeQuery();
			
			while(rs.next()) {
				OrderModel order = new OrderModel();
				order.setOrderId(rs.getInt("order_id"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderNote(rs.getString("order_note"));
                order.setFeedbackId(rs.getInt("feedback_id"));
                
                java.sql.Timestamp ts = rs.getTimestamp("order_date");
			    if (ts != null) {
			        order.setOrderDate(ts.toLocalDateTime());
			    }
			    
                orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public void updateFeedbackId(int orderId, int feedbackId) {
	    String sql = "UPDATE `order` SET feedback_id = ? WHERE order_id = ?";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setInt(1, feedbackId);
	        pst.setInt(2, orderId);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public int createOrderAndGetId(int userId, List<CartItemModel> cart, String specialInstructions,
			String preferredDate) {
		// TODO Auto-generated method stub
		return 0;
	}
}
