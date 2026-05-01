package com.quickbite.dao;

import java.util.ArrayList;
import java.util.List;
import com.quickbite.utils.DBconfig;
import com.quickbite.model.OrderOutletItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderOutletItemDAO {

	public List<OrderOutletItem> getOrdersByOutlet(int outletId) {
        List<OrderOutletItem> list = new ArrayList<>();
        String sql = "SELECT ooi.order_id, ooi.outlet_id, ooi.item_id, ooi.item_qty, ooi.order_subtotal, o.order_status"
        		+ "FROM order_outlet_item ooi JOIN `order` o ON ooi.order_id = o.order_id "
        		+ "WHERE ooi.outlet_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, outletId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	OrderOutletItem order = new OrderOutletItem(
                			rs.getInt("order_id"),
                	        rs.getInt("outlet_id"),
                	        rs.getInt("item_id"),
                	        rs.getInt("item_qty"),
                	        rs.getDouble("order_subtotal"),
                	        rs.getInt("order_status")
                	    );
                	    list.add(order);
                	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public List<OrderOutletItem> getOrderByStatus(List<OrderOutletItem> orders, int status) {
	    List<OrderOutletItem> list = new ArrayList<>();

	    for (OrderOutletItem order : orders) {
	        if (order.getOrderStatus() == status) {
	            list.add(order);
	        }
	    }

	    return list;
	}
}

