package com.quickbite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.quickbite.dao.ItemDAO;
import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Item;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;

public class MenuService {

    OutletItemDAO outletItemDAO = new OutletItemDAO();
    OutletDAO outletDAO = new OutletDAO();
    ItemDAO itemDAO = new ItemDAO();

    /**
     * Get items based on outlet selection
     * If outletId is null or empty → return all items
     * Else → return items for that specific outlet
     */
    public List<OutletItem> getMenuItems(String outletIdStr) {
        if (outletIdStr == null || outletIdStr.trim().isEmpty()) {
            // Show All Menu → Convert List<Item> to List<OutletItem> with dummy price
            List<Item> items = itemDAO.getAllItems();
            List<OutletItem> outletItems = new ArrayList<>();
            
            for (Item item : items) {
                outletItems.add(new OutletItem(item, 0.0));
            }
            return outletItems;
        } else {
            try {
                int outletId = Integer.parseInt(outletIdStr.trim());
                return outletItemDAO.getItemsByOutlet(outletId);
            } catch (NumberFormatException e) {
                // fallback
                List<Item> items = itemDAO.getAllItems();
                List<OutletItem> outletItems = new ArrayList<>();
                for (Item item : items) {
                    outletItems.add(new OutletItem(item, 0.0));
                }
                return outletItems;
            }
        }
    }

    public List<Outlet> getAllOutlets() {
        return outletDAO.getAllOutlets();
    }
}