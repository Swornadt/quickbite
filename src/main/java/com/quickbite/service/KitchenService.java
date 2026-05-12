package com.quickbite.service;

import com.quickbite.dao.OrderOutletItemDAO;

public class KitchenService {
	private OrderOutletItemDAO dao = new OrderOutletItemDAO();

    public void initiateOrder(int orderId) {
        dao.updateOrderStatus(orderId, 1);
    }

    public void markReady(int orderId) {
        dao.updateOrderStatus(orderId, 2);
    }

    public void markItemDone(int orderId, int itemId) {
        dao.updateItemStatus(orderId, itemId);
    }
}
