package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.quickbite.dao.FavoriteDAO;
import com.quickbite.dao.OrderDAO;
import com.quickbite.model.Item;
import com.quickbite.model.OrderModel;
import com.quickbite.model.UserModel;
import com.quickbite.model.OutletItem;

@WebServlet(asyncSupported = true, urlPatterns = { "/profile", "/profile/*" })
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAO();
	
    public CustomerController() {
        super();
    }

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

	private void viewChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
	}

	private void viewFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    UserModel user = (session != null) ? (UserModel) session.getAttribute("user") : null;

	    if (user == null) {
	        // Redirect to login if the session is gone
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }

	    FavoriteDAO favDAO = new FavoriteDAO();
	    try {
	        List<OutletItem> favoriteList = favDAO.getFavoritesByUser(user.getUserId());
	        request.setAttribute("favoriteList", favoriteList);
	        System.out.println(favoriteList);
	        request.getRequestDispatcher("/WEB-INF/views/customer/profile/favorites-page.jsp").forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace(); // This will show the real error in your Eclipse/IntelliJ console
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		if (endpoint == null || endpoint.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request, response);
            return;
        }
		
		switch(endpoint) {
			case "/favorites/toggle":
				toggleFavorites(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}

	private void toggleFavorites(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
	    UserModel user = (UserModel) session.getAttribute("user");

	    if (user == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return;
	    }

	    int itemId = Integer.parseInt(request.getParameter("itemId"));
	    int outletId = Integer.parseInt(request.getParameter("outletId"));
	    int userId = user.getUserId();

	    FavoriteDAO favDAO = new FavoriteDAO();
	    try {
	    	
	        if (favDAO.isFavorite(userId, itemId, outletId)) {
	            favDAO.removeFavorite(userId, itemId, outletId);
	        } else {
	            favDAO.addFavorite(userId, itemId, outletId);
	        }
	        
	        // getting the details of the previous page through header
	        String referer = request.getHeader("Referer");
	        
	        if (referer != null && !referer.isEmpty()) {
	        	response.sendRedirect(referer);
	        } else {
	        	response.sendRedirect(request.getContextPath() + "/profile/favorites");
	        }
	    } catch (SQLException | NumberFormatException e) {
	    	e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}

}
