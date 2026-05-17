package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quickbite.model.PaymentModel;
import com.quickbite.utils.DBconfig;

public class PaymentDAO {
	
	/**
	 * Creates a payment record in the database.
	 * 
	 * This method temporarily disables auto-commit, inserts payment information such as payment
	 * amount, authorization status, and current timestamp into the database, then retrieves
	 * the newly generated payment ID.
	 * 
	 * @param payment 
	 * @return the generated paymentID, or -1 if the transaction fails
	 * @throws SQLException if a core database access conflict or connectivity drop breaks the statement execution
	 */
	public int createPayment(PaymentModel payment) throws SQLException {
		String query = "INSERT INTO payment (amount, payment_status, payment_date) "+
						"VALUES (?, ?, NOW())";
		
	    Connection conn = null;
	    try {
	        conn = DBconfig.getConnection();
	        conn.setAutoCommit(false);

	        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        ps.setDouble(1, payment.getAmount());
	        ps.setString(2, payment.getPaymentStatus());
	        ps.executeUpdate();

	        // get generated paymentId
	        ResultSet rs = ps.getGeneratedKeys();
	        int paymentId = 0;
	        if (rs.next()) {
	            paymentId = rs.getInt(1);
	        }

	        conn.commit();
	        return paymentId;

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
	
	/**
	 * Lookup method to check the payment details of a specific order.
	 * 
	 * @param orderId
	 * @return PaymentModel object or null if not found
	 * @throws SQLException
	 */
	public PaymentModel getPaymentId (int paymentId) throws SQLException {
		String query = "SELECT * FROM payment WHERE order_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, paymentId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new PaymentModel(
						rs.getInt("payment_id"),
						rs.getDouble("amount"),
						rs.getString("payment_status"),
						rs.getTimestamp("payment_date")
					);
			}
		}
		return null;
	}
}
