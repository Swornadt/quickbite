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

	public List<OrderOutletItem> getOrderDetail(int orderId, int outletId) {
        List<OrderOutletItem> list = new ArrayList<>();
        String sql ="SELECT ooi.order_id, ooi.outlet_id, ooi.item_id, ooi.item_qty, ooi.item_status, " +
        	    	"o.order_status, o.order_date, o.order_note, o.preferred_date, " +
        	    	"i.item_name, ol.outlet_name " +
        	    	"FROM order_outlet_item ooi " +
        	    	"JOIN `order` o ON ooi.order_id = o.order_id " +
        	    	"JOIN item i ON ooi.item_id = i.item_id " +
        	    	"JOIN outlet ol ON ooi.outlet_id = ol.outlet_id " +
        	    	"WHERE ooi.order_id = ? AND ooi.outlet_id = ?";
        
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, outletId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new OrderOutletItem(
                    	rs.getInt("order_id"),           
                   	    rs.getInt("outlet_id"),           
                		rs.getInt("item_id"),             
                		rs.getInt("item_status"),         
                   	    rs.getString("item_name"),        
                   	    rs.getInt("item_qty"),            
                        rs.getInt("order_status"),        
                        rs.getTimestamp("order_date"),    
                        rs.getTimestamp("preferred_date"),
                        rs.getString("order_note"),       
                        rs.getString("outlet_name")   
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public List<OrderOutletItem> getOrdersByOutlet(int outletId) {
	    List<OrderOutletItem> list = new ArrayList<>();
	    String sql =
	        "SELECT DISTINCT ooi.order_id, ooi.outlet_id, o.order_status "
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
	
	public void updateOrderStatus(int orderId, int status) {
	    String sql = "UPDATE `order` SET order_status = ? WHERE order_id = ?";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, status);
	        ps.setInt(2, orderId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void updateItemStatus(int orderId, int itemId) {
	    String sql = "UPDATE order_outlet_item SET item_status = 1 WHERE order_id = ? AND item_id = ?";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, orderId);
	        ps.setInt(2, itemId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}

