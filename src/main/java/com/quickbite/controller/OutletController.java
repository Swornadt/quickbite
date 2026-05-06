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
 * Servlet implementation class OutletServlet
 */
@WebServlet("/outlets/*")
public class OutletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			handleOutlet(request,response);
		} else {
			handleOutletMenu(request,response, path.substring(1));
		}
	}
		
	private void handleOutlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		OutletDAO dao = new OutletDAO();
		List<Outlet> list = dao.getAllOutlets();
		
		request.setAttribute("locations", list);
		request.getRequestDispatcher("/WEB-INF/views/customer/outlet.jsp").forward(request, response);
	}
	
	private void handleOutletMenu(HttpServletRequest request, HttpServletResponse response, String outletName) throws ServletException, IOException {

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
	    
	    System.out.println("DEBUG outlet: " + (outlet == null ? "NULL" : outlet.getOutletName()));
	    System.out.println("DEBUG results size: " + results.size());
	    System.out.println("DEBUG categories size: " + categories.size());
	    
	    request.getRequestDispatcher("/WEB-INF/views/customer/location-menu.jsp").forward(request, response);
} 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
