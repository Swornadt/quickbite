package com.quickbite.service;

import com.quickbite.dao.OrderOutletItemDAO;

public class KitchenService {
	private OrderOutletItemDAO dao = new OrderOutletItemDAO();

	/**
	 * Flags an incoming order ticket as actively under preparation in the kitchen
	 * 
	 * @param orderId the id matching the order of the customer.
	 * @param outletId the id belonging to the outlet for which the order was intended for.
	 */
    public void initiateOrder(int orderId, int outletId) {
    	  dao.updateOutletOrderStatus(orderId, outletId, 1);
          dao.updateOrderStatus(orderId, 1);
    }

    /**
     * Attempts to finalize an entire order ticket for a specific branch location.
     * 
     * @param orderId the id matching the order of the customer.
	 * @param outletId the id belonging to the outlet for which the order was intended for.
     */
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
    
    /**
     * 
     * @param orderId
     * @param itemId
     */

    public void markItemDone(int orderId, int itemId) {
        dao.updateItemStatus(orderId, itemId);
    }
}
