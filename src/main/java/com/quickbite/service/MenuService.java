package com.quickbite.service;

import java.util.List;

import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;

public class MenuService {

    OutletItemDAO outletItemDAO = new OutletItemDAO();
    OutletDAO outletDAO = new OutletDAO();

    /**
     * Get items based on outlet selection
     * If outletId is null or empty → return all items
     * Else → return items for that specific outlet
     */
    public List<OutletItem> getMenuItems(String outletIdStr) {
        if (outletIdStr == null || outletIdStr.trim().isEmpty()) {
            // Default to first outlet if none selected
            List<Outlet> outlets = outletDAO.getAllOutlets();
            if (!outlets.isEmpty()) {
                return outletItemDAO.getItemsByOutlet(outlets.get(0).getOutletId());
            }
            return List.of(); // empty list if no outlets
        }

        try {
            int outletId = Integer.parseInt(outletIdStr.trim());
            return outletItemDAO.getItemsByOutlet(outletId);
        } catch (NumberFormatException e) {
            // fallback to first outlet
            List<Outlet> outlets = outletDAO.getAllOutlets();
            if (!outlets.isEmpty()) {
                return outletItemDAO.getItemsByOutlet(outlets.get(0).getOutletId());
            }
            return List.of();
        }
    }

    public List<Outlet> getAllOutlets() {
        return outletDAO.getAllOutlets();
    }
}