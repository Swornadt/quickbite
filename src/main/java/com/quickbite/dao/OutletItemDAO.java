package com.quickbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.Item;
import com.quickbite.model.OutletItem;
import com.quickbite.utils.DBconfig;

public class OutletItemDAO {

	public List<OutletItem> getItemsByOutlet(int outletId){
		List<OutletItem> outletItems=new ArrayList<>();
		String sql= "select i.*, oi.outlet_item_price " +
                "from item i " +
                "join outlet_item oi ON i.item_id = oi.item_id " +
                "where oi.outlet_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, outletId);
			
			try(ResultSet rs= ps.executeQuery()){
				while (rs.next()) {
					Item item = new Item(
		                    rs.getInt("item_id"),
		                    rs.getString("item_name"),
		                    rs.getString("category"),
		                    rs.getString("item_type"),
		                    rs.getString("item_description"),
		                    rs.getString("item_status"),
		                    rs.getString("item_ingredient"),
		                    rs.getString("item_allergy"),
		                    rs.getString("item_image")
		                );
					OutletItem outletItem = new OutletItem(item, rs.getDouble("outlet_item_price"));
					outletItems.add(outletItem);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());		}
		return outletItems;
	}
}
