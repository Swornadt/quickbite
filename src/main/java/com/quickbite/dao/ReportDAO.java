package com.quickbite.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.quickbite.model.ReportModel;
import com.quickbite.utils.DBconfig;

public class ReportDAO {
	
	/**
	 * Compiles metric aggregates into a unified analytics data report model.
	 * 
	 * Constructs conditional SQL selection filters depending on 
	 * whether the execution context targets a specific outlet or requests system-wide insights. 
	 * It executes multiple database queries to calculate the total number of active users,
	 * total orders, and overall revenue, then stores the results in a ReportModel object.
	 * 
	 * @param outletId the unique id for the outlet selected for report generation.
	 * @return a populated ReportModel containing aggregated user count, total orders, and sales metrics
	 */
	public ReportModel getReport(int outletId) {
		ReportModel report = new ReportModel();
		
		try (Connection conn = DBconfig.getConnection()) {
			Statement st = conn.createStatement();
			String condition = "";
			if (outletId > 0) {
				condition = " WHERE ooi.outlet_id = " + outletId;
			}

            // Total Users (same for all)
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM user WHERE status = 'Active'");
            if (rs.next()) report.setTotalUsers(rs.getInt(1));
            
            // Total Orders
            String orderSql = "SELECT COUNT(DISTINCT o.order_id) FROM `order` o " +
                            "JOIN order_outlet_item ooi ON o.order_id = ooi.order_id" + 
                            condition;
            rs = st.executeQuery(orderSql);
            if (rs.next()) report.setTotalOrders(rs.getInt(1));

            // Total Sales
            String salesSql = "SELECT COALESCE(SUM(ooi.order_subtotal), 0) FROM order_outlet_item ooi" 
                            + condition;
            rs = st.executeQuery(salesSql);
            if (rs.next()) report.setTotalSales(rs.getDouble(1));
            
            }


        catch (Exception e) {
            e.printStackTrace();
        }
        return report;
		}
	}

