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
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
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
	 * @param request
	 * @param response
	 * @param outletName
	 * @throws ServletException
	 * @throws IOException
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
