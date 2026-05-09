package com.quickbite.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.quickbite.model.Report;
import com.quickbite.utils.DBconfig;

public class ReportDAO {
	
	public Report getReport(int outletId) {
		Report report = new Report();
		
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

