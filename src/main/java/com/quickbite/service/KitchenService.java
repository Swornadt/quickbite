package com.quickbite.service;

import com.quickbite.dao.OrderOutletItemDAO;

public class KitchenService {
	private OrderOutletItemDAO dao = new OrderOutletItemDAO();

    public void initiateOrder(int orderId, int outletId) {
    	  dao.updateOutletOrderStatus(orderId, outletId, 1);
          dao.updateOrderStatus(orderId, 1);
    }

    public boolean markReady(int orderId, int outletId) {
        if (dao.getItemsStatus(orderId, outletId)) {
            dao.updateOutletOrderStatus(orderId, outletId, 2);
            if (dao.getAllOutletOrderStatus(orderId)) {
                dao.updateOrderStatus(orderId, 2);
            }
            return true;
        }
        return false;
    }

    public void markItemDone(int orderId, int itemId) {
        dao.updateItemStatus(orderId, itemId);
    }
}
