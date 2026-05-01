package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.quickbite.dao.ItemDAO;
import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Item;
import com.quickbite.utils.ImageUtil;

/**
 * Servlet implementation class UpdateItem
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditItem" })
@MultipartConfig()

public class EditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItem() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String itemIdStr = request.getParameter("itemId");


        if (itemIdStr == null || itemIdStr.trim().isEmpty()) {
            request.setAttribute("message", "Item ID is missing!");
            request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr.trim());

            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.getItemById(itemId);

            if (item != null) {
                request.setAttribute("item", item);
            } else {
                request.setAttribute("message", "Item not found with ID: " + itemId);
            }

            // Load outlets for dropdown
            OutletDAO outletDAO = new OutletDAO();
            request.setAttribute("outlets", outletDAO.getAllOutlets());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error loading item data: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {


        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String category = request.getParameter("category");
            String itemType = request.getParameter("itemType");
            String itemDescription = request.getParameter("itemDescription");
            String itemStatus = request.getParameter("itemStatus");
            String itemIngredient = request.getParameter("itemIngredient");
            String itemAllergy = request.getParameter("itemAllergy");
            String existingImage = request.getParameter("existingImage");

            String imagePath = (existingImage != null && !existingImage.trim().isEmpty()) 
                                ? existingImage : "";

            // Handle image upload safely
            if (request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart")) {
                try {
                    Part part = request.getPart("itemImage");
                    if (part != null && part.getSize() > 0) {
                        ImageUtil imageUtil = new ImageUtil();
                        imagePath = imageUtil.uploadProfileImage(part, "uploads/items", getServletContext());
                        
                    }
                } catch (Exception ex) {
                    System.out.println("DEBUG: No new image or error uploading image");
                }
            }

            // Create Item object
            Item item = new Item(itemId, itemName, category, itemType, 
                               itemDescription, itemStatus, itemIngredient, 
                               itemAllergy, imagePath);


            ItemDAO itemDAO = new ItemDAO();
            boolean updated = itemDAO.updateItem(item);

            if (updated) {
                request.setAttribute("message", "Item updated successfully!");
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("message", "Failed to update item in database.");
                request.setAttribute("status", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error updating item: " + e.getMessage());
            request.setAttribute("status", "error");
        }

        doGet(request, response);
    }


}
