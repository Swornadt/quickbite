package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/outlets/*")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String outletName = request.getPathInfo().substring(1);
		String selectedCategory = request.getParameter("category");
	    String searchQuery = request.getParameter("search");
		
		
	    OutletDAO outletDAO = new OutletDAO();
	    Outlet outlet = outletDAO.getOutletByName(outletName);
	    
	    if (outlet==null) {
	    	response.sendRedirect(request.getContextPath() + "/outlets");
	    	return;
	    }
	    
	    OutletItemDAO outletItemDAO = new OutletItemDAO();
	    
	    
	    //Getting all items first to populate category buttons
	    List<OutletItem> allItems = outletItemDAO.getItemsByOutlet(outlet.getOutletId());
	    List<String> categories = allItems.stream().map(oi -> oi.getItem().getCategory()).distinct().filter(cat -> cat != null && !cat.isEmpty()).toList();
 
	    //Get only the items for selected categories
	    List<OutletItem> results = outletItemDAO.searchItems(outlet.getOutletId(), selectedCategory, searchQuery);  
	    
	    request.setAttribute("categories", categories);
	    request.setAttribute("outlet", outlet);
	    request.setAttribute("outletItems", results);
	    
	    request.getRequestDispatcher("/WEB-INF/views/customer/location-menu.jsp")
	           .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
