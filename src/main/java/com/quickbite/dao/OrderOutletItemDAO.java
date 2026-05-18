package com.quickbite.dao;

import java.util.ArrayList;
import java.util.List;
import com.quickbite.utils.DBconfig;
import com.quickbite.model.OrderOutletItemModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderOutletItemDAO {

	/**
 	 * Retrieves all order details belonging to a specific order of an specific outlet
 	 * 
 	 * Executes a JOIN query connecting order_outlet_item, order, item, and outlet tables 
 	 * 
 	 * @param orderId  identitfes which orders details to retrieve
 	 * @param outletId identifies which outlet the order belongs to
 	 * @return a List containing fully populated OrderOutletItemModel attributes of the required order and the outlet
 	 */
	public List<OrderOutletItemModel> getOrderDetail(int orderId, int outletId) {
        List<OrderOutletItemModel> list = new ArrayList<>();
        String sql ="SELECT ooi.order_id, ooi.outlet_id, ooi.item_id, ooi.item_qty, ooi.item_status, ooi.outlet_order_status, " +
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
                    list.add(new OrderOutletItemModel(
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
                        rs.getString("outlet_name"),  
                        rs.getInt("outlet_order_status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	

	/**
	 * Retrieves all orders assigned to a specific outlet for the current date.
	 * 
	 * Executes a DISTINCT query to avoid duplicate entries caused by repeating item rows,
 	 * filtering by outlet and displaying only today's order by comparing today's date with either the order date or preferred date.
	 * 
	 * @param outletId identifies which outlets orders to fecth
	 * @return a List containing OrderOutletItemModel entries representing each unique order for today
	 */
	public List<OrderOutletItemModel> getOrdersByOutlet(int outletId) {
	    List<OrderOutletItemModel> list = new ArrayList<>();
	    String sql =
	    		"SELECT DISTINCT ooi.order_id, ooi.outlet_id, ooi.outlet_order_status " +
	    	    "FROM order_outlet_item ooi " +
	    	    "JOIN `order` o ON ooi.order_id = o.order_id " +
	    	    "WHERE ooi.outlet_id = ? AND (" +
	    	    "(o.preferred_date IS NULL AND DATE(o.order_date) = CURDATE()) OR " +
	    	    "(o.preferred_date IS NOT NULL AND DATE(o.preferred_date) = CURDATE())" +
	    	    ")";
	    
	    System.out.println("SQL running: " + sql);
	    
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, outletId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                OrderOutletItemModel order = new OrderOutletItemModel(
	                    rs.getInt("order_id"),
	                    rs.getInt("outlet_id"),
	                    rs.getInt("outlet_order_status")
	                );
	                list.add(order);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	

	/**
	 * Filters a list of orders according to their status.
	 * 
	 * Iterates the provided order list and returns only entries matching the given status.
	 *
	 * @param orders the List of OrderOutletItemModel entries to filter
	 * @param status the integer status used to filter the list entries
	 * @return a List containing only orders matching the specified status
	 */
	public List<OrderOutletItemModel> getOrderByStatus(List<OrderOutletItemModel> orders, int status) {
	    List<OrderOutletItemModel> list = new ArrayList<>();

	    for (OrderOutletItemModel order : orders) {
	        if (order.getOutletOrderStatus() == status) {
	            list.add(order);
	        }
	    }

	    return list;
	}
	

	/**
	 * Updates the status of an order in the database.
 	 * 	
 	 * @param orderId identifies the order that needs to be updated
 	 * @param status  the new status to update the order with
 	 */
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


	/**
 	 * Updates the outlet-level status of specific orders.
 	 * 
 	 * @param orderId  identifies the order to be updated
 	 * @param outletId identifies the outlet of the order
 	 * @param status   the new status that will be assigned
 	 */
	public void updateOutletOrderStatus(int orderId, int outletId, int status) {
	    String sql = "UPDATE order_outlet_item SET outlet_order_status = ? WHERE order_id = ? AND outlet_id = ?";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, status);
	        ps.setInt(2, orderId);
	        ps.setInt(3, outletId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	/**
	 * Updates the status of item of an order as complete.
 	 * 
 	 * @param orderId identifies the order containing the item
 	 * @param itemId  identifies the item to mark as complete
 	 */
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
	

	/**
 	 * Checks whether an order with multiple outlets has been completed in all the each respective outlet.
 	 * 
 	 * @param orderId identifies the order to check
 	 * @return true if order has been completed in all outlets; false otherwise
 	 */
	public boolean getAllOutletOrderStatus(int orderId) {
	    String sql = "SELECT COUNT(*) FROM order_outlet_item WHERE order_id = ? AND outlet_order_status != 2";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, orderId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) return rs.getInt(1) == 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	/**
 	 * Checks whether all items within an order of an outlet is ready.
 	 * 
 	 * @param orderId  identifies the order to check
 	 * @param outletId identifies the outlet to check
 	 * @return true if no pending items remain; false otherwise
 	 */
	public boolean getItemsStatus(int orderId, int outletId) {
	    String sql = "SELECT COUNT(*) FROM order_outlet_item WHERE order_id = ? AND outlet_id = ? AND item_status = 0";
	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, orderId);
	        ps.setInt(2, outletId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) return rs.getInt(1) == 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
}
