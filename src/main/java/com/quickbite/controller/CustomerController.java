package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.quickbite.dao.OrderDAO;
import com.quickbite.model.OrderModel;
import com.quickbite.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/profile", "/profile/*" })
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAO();
	
    public CustomerController() {
        super();
    }

    /**
     * Handles GET requests for User Profile's actions
     * 
     * Based on the endpoint of the current URL, the corresponding method is called.
     * 1. Profile
     * 2. Order History
     * 3. Change Password
     * 4. View Favorites
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     * @see #viewChangePassword(HttpServletRequest, HttpServletResponse)
     * @see #viewCustomerOrderHistory(HttpServletRequest, HttpServletResponse)
     * @see #viewFavorites(HttpServletRequest, HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		if (endpoint == null || endpoint.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request, response);
            return;
        }
		
		switch(endpoint) {
			case "/order-history":
				viewCustomerOrderHistory(request, response);
				break;
			case "/change-password":
				viewChangePassword(request, response);
				break;
			case "/favorites":
				viewFavorites(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}

	/**
	 * Displays the past and ongoing orders of the current user.
	 * 
	 * Gets the user's details from the logged in session, and fetches
	 * the past and current orders through the DAO, passing true and false
	 * for current and past respectively.
	 * The data is then passed to jsp through attributes.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void viewCustomerOrderHistory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// fetch data by dao
		List<OrderModel> currentOrders = orderDAO.getOrdersByStatus(user.getUserId(), true);
        List<OrderModel> pastOrders = orderDAO.getOrdersByStatus(user.getUserId(), false);
        
        // setting those attributes to jsp
        request.setAttribute("currentOrders", currentOrders);
        request.setAttribute("pastOrders", pastOrders);
        
		request.getRequestDispatcher("/WEB-INF/views/customer/profile/order-history.jsp").forward(request, response);		
	}

	private void viewChangePassword(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void viewFavorites(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
