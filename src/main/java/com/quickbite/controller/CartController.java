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
import com.quickbite.model.CartItemModel;
import com.quickbite.model.OutletItemModel;
import com.quickbite.model.UserModel;
import com.quickbite.service.CartService;

@WebServlet(asyncSupported = true, urlPatterns = { "/cart","/cart/*" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
       
    public CartController() {
        super();
    }

    /**
     * Handles GET request to display customer's cart.
     * This method instantiates cartService which fetches the cart stored in the 
     * user's session and stores it as a List.
     * Additionally, the subtotal is calculated and both are forwarded
     * as attributes.
     * 
     * @param request
     * @param response
     * @return void
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		HttpSession session = request.getSession();
		UserModel user = (session != null) ? (UserModel) session.getAttribute("user") : null;

	    if (user != null) {
	        FavoriteDAO favDAO = new FavoriteDAO();
	        try {
	            // Fetch the user's favorites to compare against items on the page
	            List<OutletItemModel> favoriteList = favDAO.getFavoritesByUser(user.getUserId());
	            request.setAttribute("favoriteList", favoriteList); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
		// get current cart from the session
		List<CartItemModel> cart = cartService.getCart(session);
		
		// calculate the subtotal
		double subtotal = cartService.calculateSubtotal(cart);
		
		// passing attributes
		request.setAttribute("userCart", cart);
		request.setAttribute("subtotal", subtotal);
		
		request.getRequestDispatcher("/WEB-INF/views/customer/cart.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by routing them to corresponding cart management methods.
	 * 
	 * Based on the URL path, the method dispatches the request
	 * to specialized handlers for CRUD operations of items within the cart.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 * @see #handleAddToCart(HttpServletRequest, HttpServletResponse)
	 * @see #handleRemoveFromCart(HttpServletRequest, HttpServletResponse)
	 * @see #handleUpdateQuantity(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		if (endpoint==null || endpoint.equals("/")) {
			doGet(request, response);
		} else if (endpoint.equals("/add")) {
			handleAddToCart(request, response);
		} else if (endpoint.equals("/remove")) {
			handleRemoveFromCart(request, response);
		} else if (endpoint.equals("/update")) {
			handleUpdateQuantity(request, response);
		}
	}
	
	/**
	 * Handles addition of a specific menu item to the user's in-session cart.
	 * 
	 * It extracts the item details and price from the request parameters,
	 * encapsulates them into CartItemModel, and utilizes CartService to
	 * persist the item in the current session.
	 * Upon success, it redirects the user back to the originating page
	 * using Referer with a success flag appended to the URL to trigger
	 * UI feedback.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleAddToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			// extract data
			int itemId = Integer.parseInt(request.getParameter("itemId"));
            int outletId = Integer.parseInt(request.getParameter("outletId"));
            String outletName = request.getParameter("outletName");
            String itemName = request.getParameter("itemName");
            double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            // create model
            CartItemModel newItem = new CartItemModel(itemId, outletId, outletName, itemName, unitPrice, quantity);
            
            // update session via service
            HttpSession session = request.getSession(); 
            cartService.addToCart(session, newItem);
            
            // get source url
            String referer = request.getHeader("Referer");
            System.out.println(referer);
            String url;
            // append success param
            if (referer != null && !referer.isEmpty()) {
            	url = referer.contains("?") ? referer + "&added=true" : referer + "?added=true";
            } else {
            	// fallback to home
            	url = request.getContextPath()+"/home?added=true";
            }
            
            response.sendRedirect(url);
            return;
            
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/home");
		}
	}
	
	/**
	 * Proceses removal of a specific menu item from the user's in-session cart.
	 * 
	 * It gets the ID of the item that is to be removed, and utilizes the
	 * cartService to remove the item completely, regardless of the quantity.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleRemoveFromCart (HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			cartService.removeFromCart(request.getSession(), itemId);
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/cart?error=true");
		}
	}
	
	/**
	 * Handles the update request from the user to update the quantity
	 * of item in the in-session cart.
	 * 
	 * It extracts the id of the menu item and the amount by which to update.
	 * The amount can be negative or positive based on which button was clicked
	 * on the UI. The update is done via cartService and redirected to cart page.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleUpdateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			cartService.updateQuantity(request.getSession(), itemId, amount);
			response.sendRedirect(request.getContextPath() + "/cart");
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/cart?error=true");
		}
	}

}
