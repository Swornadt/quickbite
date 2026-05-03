package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.dao.ItemDAO;
import com.quickbite.dao.OutletItemDAO;

/**
 * Servlet implementation class DeleteItem
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteItem" })
public class DeleteItem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/admin-delete-item.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemIdStr = request.getParameter("itemId");
        String action = request.getParameter("action");

        if (!"delete".equals(action)) {
            doGet(request, response);
            return;
        }
        try {

            int itemId = Integer.parseInt(itemIdStr.trim());

            ItemDAO itemDAO = new ItemDAO();
            OutletItemDAO outletItemDAO = new OutletItemDAO();

            // Step 1: Delete from outlet_item first
            outletItemDAO.deleteOutletItemsByItemId(itemId);

            // Step 2: Delete the item
            boolean itemDeleted = itemDAO.deleteItem(itemId);

            if (itemDeleted) {
                request.setAttribute("message", "Item deleted successfully!");
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("message", "Failed to delete item.");
                request.setAttribute("status", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error deleting item: " + e.getMessage());
            request.setAttribute("status", "error");
        }

        doGet(request, response);
    }

}
