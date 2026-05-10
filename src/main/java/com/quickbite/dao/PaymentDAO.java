package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quickbite.model.PaymentModel;
import com.quickbite.utils.DBconfig;

public class PaymentDAO {
	
	public boolean createPayment(PaymentModel payment) throws SQLException {
		String query = "INSERT INTO payment (order_id, amount, payment_status, payment_date) "+
						"VALUES (?, ?, ?, ?)";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setInt(1, payment.getOrderId());
			ps.setDouble(2, payment.getAmount());
			ps.setString(3,  payment.getPaymentStatus());
			ps.setTimestamp(4, payment.getPaymentDate());
			return ps.executeUpdate() > 0;
		}
	}
	
	/**
	 * Lookup method to check the payment details of a specific order.
	 * 
	 * @param orderId
	 * @return PaymentModel object or null if not found
	 * @throws SQLException
	 */
	public PaymentModel getPaymentByOrderId (int orderId) throws SQLException {
		String query = "SELECT * FROM payment WHERE order_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new PaymentModel(
						rs.getInt("payment_id"),
						rs.getInt("order_id"),
						rs.getDouble("amount"),
						rs.getString("payment_status"),
						rs.getTimestamp("payment_date")
					);
			}
		}
		return null;
	}
}
