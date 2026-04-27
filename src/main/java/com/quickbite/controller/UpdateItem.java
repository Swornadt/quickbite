package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.model.Item;

/**
 * Servlet implementation class UpdateItem
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateItem" })
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItem() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		String category = request.getParameter("category");
		String itemType = request.getParameter("itemType");
		Double price = Double.parseDouble(request.getParameter("price"));
		String itemDescription = request.getParameter("itemDescription");
		String itemStatus = request.getParameter("Status");
		String itemIngredient = request.getParameter("itemIngredient");
		String itemAllergy = request.getParameter("itemAllergy");
		String itemImage = request.getParameter("itemImage");
		
		Item item = new Item(itemId, itemName, category, itemType, itemDescription, itemStatus, itemIngredient,
	           itemAllergy, itemImage);
		
	}

}
