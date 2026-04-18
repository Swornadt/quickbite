package com.quickbite.service;

import com.quickbite.model.CartItemModel;
import com.quickbite.model.UserModel;
import com.quickbite.utils.DBconfig;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    // Retrieve the cart from session, or create a new one if it doesn't exist
    @SuppressWarnings("unchecked")
    public List<CartItemModel> getCart(HttpSession session) {
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, CartItemModel newItem) {
        List<CartItemModel> cart = getCart(session);
        
        // Check if item already exists, if so, just update quantity
        for (CartItemModel item : cart) {
            if (item.getItemId() == newItem.getItemId()) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        cart.add(newItem);
    }

    public double calculateSubtotal(List<CartItemModel> cart) {
        return cart.stream().mapToDouble(CartItemModel::getTotalPrice).sum();
    }

	public boolean placeOrder(UserModel user, List<CartItemModel> cart, String specialInstructions, String deliveryTimeType, String deliveryDate, String timeSlot) throws Exception{
		if (user == null || cart == null || cart.isEmpty()) {
	        return false;
	    }

	    Connection con = null;
	    try {
	        con = DBconfig.getConnection();
	        con.setAutoCommit(false);   // Important for data consistency

	        // 1. Insert main Order
	        String orderSql = "INSERT INTO `Order` (user_id, order_date, order_status, order_note) "
	                        + "VALUES (?, NOW(), 1, ?)";   // status 1 = Pending

	        int orderId;
	        try (PreparedStatement pst = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
	            pst.setInt(1, user.getUserId());
	            pst.setString(2, specialInstructions != null ? specialInstructions : "");

	            pst.executeUpdate();

	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    orderId = rs.getInt(1);
	                } else {
	                    throw new SQLException("Failed to create order");
	                }
	            }
	        }

	        // 2. Insert Order Items
	        String itemSql = "INSERT INTO Order_Outlet_Item "
	                       + "(order_id, outlet_id, item_id, item_qty, order_subtotal) "
	                       + "VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement pst = con.prepareStatement(itemSql)) {
	            for (CartItemModel item : cart) {
	                double subtotal = item.getUnitPrice() * item.getQuantity();

	                pst.setInt(1, orderId);
	                pst.setInt(2, item.getOutletId());     // Important: using outlet_id from cart
	                pst.setInt(3, item.getItemId());
	                pst.setInt(4, item.getQuantity());
	                pst.setDouble(5, subtotal);

	                pst.executeUpdate();
	            }
	        }

	        con.commit();
	        System.out.println("Order placed successfully! Order ID = " + orderId);
	        return true;

	    } catch (Exception e) {
	        if (con != null) con.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (con != null) {
	            con.setAutoCommit(true);
	            con.close();
	        }
	    }
	}
}