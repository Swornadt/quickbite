package com.quickbite.service;

import java.util.List;

import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.OutletModel;
import com.quickbite.model.OutletItemModel;

public class MenuService {

    OutletItemDAO outletItemDAO = new OutletItemDAO();
    OutletDAO outletDAO = new OutletDAO();

    /**
     * Get items based on outlet selection
     * If outletId is null or empty → return all items
     * Else → return items for that specific outlet
     */
    public List<OutletItemModel> getMenuItems(String outletIdStr) {
        if (outletIdStr == null || outletIdStr.trim().isEmpty()) {
            // Default to first outlet if none selected
            List<OutletModel> outlets = outletDAO.getAllOutlets();
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
            List<OutletModel> outlets = outletDAO.getAllOutlets();
            if (!outlets.isEmpty()) {
                return outletItemDAO.getItemsByOutlet(outlets.get(0).getOutletId());
            }
            return List.of();
        }
    }

    public List<OutletModel> getAllOutlets() {
        return outletDAO.getAllOutlets();
    }
}