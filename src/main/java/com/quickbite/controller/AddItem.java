package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

import com.quickbite.dao.ItemDAO;
import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Item;
import com.quickbite.model.Outlet;
import com.quickbite.utils.ImageUtil;

/**
 * Servlet implementation class AddItem
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddItem" })
@MultipartConfig(fileSizeThreshold = 1024 * 1-24 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OutletDAO outletDAO = new OutletDAO();
        List<Outlet> outlets = outletDAO.getAllOutlets();
        request.setAttribute("outlets", outlets);
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            String itemName = request.getParameter("itemName");
            String priceStr = request.getParameter("price");
            String outletIdStr = request.getParameter("outletId");

            if (itemName == null || itemName.trim().isEmpty() || 
                priceStr == null || outletIdStr == null) {
                request.setAttribute("message", "Item Name, Price and Outlet are required");
                request.setAttribute("status", "error");
                forward(request, response);
                return;
            }

            // Image
            Part part = request.getPart("itemImage");
            ImageUtil imageUtil = new ImageUtil();
            String imagePath = imageUtil.uploadProfileImage(part, "uploads/items", getServletContext());

            ItemDAO itemDAO = new ItemDAO();
            OutletItemDAO outletItemDAO = new OutletItemDAO();

            int itemId = -1;

            // Check by name only
            Item existing = itemDAO.getItemByName(itemName.trim());

            if (existing != null) {
                itemId = existing.getItemId();
                request.setAttribute("message", "Item already exists. Linking to new outlet...");
            } else {
                Item newItem = new Item(
                    itemName.trim(),
                    request.getParameter("category"),
                    request.getParameter("itemType"),
                    request.getParameter("itemDescription"),
                    request.getParameter("itemStatus"),
                    request.getParameter("itemIngredient"),
                    request.getParameter("itemAllergy"),
                    imagePath
                );

                itemId = itemDAO.addItemAndReturnId(newItem);
                request.setAttribute("message", "New item created successfully!");
            }

            // Link to outlet_item
            if (itemId > 0) {
                int outletId = Integer.parseInt(outletIdStr.trim());
                double price = Double.parseDouble(priceStr.trim());

                boolean linked = outletItemDAO.addOrUpdateOutletItem(outletId, itemId, price);

                if (linked) {
                	request.setAttribute("message", "Item successfully linked to outlet " + outletId + " with price " + price);
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("message", "Item saved but failed to link to outlet");
                    request.setAttribute("status", "warning");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("status", "error");
        }
        
        loadOutlets(request);

        forward(request, response);
    }
	
	private void loadOutlets(HttpServletRequest request) {
        OutletDAO outletDAO = new OutletDAO();
        java.util.List<com.quickbite.model.Outlet> outlets = outletDAO.getAllOutlets();
        request.setAttribute("outlets", outlets);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(req, resp);
    }

}
