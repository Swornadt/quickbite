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
        String sql = "SELECT order_id, outlet_id, item_id, item_qty, order_subtotal FROM order_outlet_item WHERE outlet_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, outletId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	while (rs.next()) {
                	    OrderOutletItem item = new OrderOutletItem(
                	        rs.getInt("order_id"),
                	        rs.getInt("outlet_id"),
                	        rs.getInt("item_id"),
                	        rs.getInt("item_qty"),
                	        rs.getDouble("order_subtotal")
                	    );
                	    list.add(item);
                	}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

