package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.OutletModel;
import com.quickbite.model.OutletItemModel;

@WebServlet("/outlets/*")
public class OutletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OutletController() {
        super();
    }

    /**
   	 * Handles HTTP GET requests to manage and route outlet discovery or location menus.
   	 * 
   	 * Reads the sub-path information from the request URL. 
   	 * If no additional path segment exists, it forwards to primary location selection dashboard. 
   	 * Otherwise, it extracts the target location branch name substring, and routes processing to assemble 
   	 * that specific branch's dynamic menu.
   	 * 
   	 * @param request the HTTP request containing the path information used for routing.
	 * @param response the HTTP response used to forward the user to the proper page view.
	 * @throws ServletException if connection is disturbed mid-way.   
   	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			handleOutlet(request,response);
		} else {
			handleOutletMenu(request,response, path.substring(1));
		}
	}
	
	/**
	 * Displays Main Outlet Selection Page
	 * 
	 * Fetches all available outlets from the database and forwards it to the Outlet View
	 * 
	 * @param request the HTTP request container used to hold the compiled list of locations.
	 * @param response the HTTP response handler used to render the location selection panel.
	 * @throws ServletException if the view renderer runs into an exception loading the outlet JSP.
	 * @throws IOException if a network connection drop prevents sending the page back to the user.
	 */
	private void handleOutlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		//Instantiating DAO
		OutletDAO dao = new OutletDAO();
		List<OutletModel> list = dao.getAllOutlets();
		
		request.setAttribute("locations", list);
		request.getRequestDispatcher("/WEB-INF/views/customer/outlet.jsp").forward(request, response);
	}

	
	/**
	 * Displays Menu for selected outlet
	 * 
	 * Validates if the outlet exists in the database
	 *
	 * Dynamically extracts all categories (unique)
	 * 
	 * Extracts query parameters from the URL to display results for selected category or item name
	 * 
	 * @param request the HTTP request containing search text or category choices.
	 * @param response the HTTP response used to deliver the food menu panel.
	 * @param outletName the unique textual identifier name matching the requested outlet.
	 * @throws ServletException if system components fail while drawing the menu dashboard JSP.
	 * @throws IOException if an processing error interrupts writing data back to the browser.
	 */
	private void handleOutletMenu(HttpServletRequest request, HttpServletResponse response, String outletName) throws ServletException, IOException {
		//Extracts the query parameters from the URL (for filtering and searching)
		String selectedCategory = request.getParameter("category");
	    String searchQuery = request.getParameter("search");
		
	    //Fetching the corresponding Outlet Object from the database using the outlet name
	    OutletDAO outletDAO = new OutletDAO();
	    OutletModel outlet = outletDAO.getOutletByName(outletName);
	    
	    //Redirecting to the outlets page in case of missing outlet
	    if (outlet==null) {
	    	response.sendRedirect(request.getContextPath() + "/outlets");
	    	return;
	    }
	    
	    OutletItemDAO outletItemDAO = new OutletItemDAO();
	    
	    //Getting all items first to populate category buttons
	    List<OutletItemModel> allItems = outletItemDAO.getItemsByOutlet(outlet.getOutletId());
	    
	    List<String> categories = new ArrayList<>();
	    
	    //To get categories from each Item
	    for (OutletItemModel oi : allItems) {
	    	String cat = oi.getItem().getCategory();
	    	
	    	//Filters out null categories and stores unique categories
	    	if (cat !=null && !cat.isEmpty()) {
	    		if(!categories.contains(cat)) {
	    			categories.add(cat);
	    		}
	    	}
	    }
	    
	    //Get only the items for selected categories
	    List<OutletItemModel> results = outletItemDAO.searchItems(outlet.getOutletId(), selectedCategory, searchQuery);  
	    
	    //Attaching all compiled data objects as attributes to the requests
	    request.setAttribute("categories", categories);
	    request.setAttribute("outlet", outlet);
	    request.setAttribute("outletItems", results);
	    
	    request.getRequestDispatcher("/WEB-INF/views/customer/location-menu.jsp").forward(request, response);
} 

	/**
	 * Handles HTTP POST requests by delegating processing logic to the doGet method.
	 * 
	 * @param request the HTTP request context container passing client details.
	 * @param response the HTTP response routing engine used to push view states back.
	 * @throws ServletException if the underlying controller router runs into an internal exception.
	 * @throws IOException if an error happens while streaming the response payload back to the browser.
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
