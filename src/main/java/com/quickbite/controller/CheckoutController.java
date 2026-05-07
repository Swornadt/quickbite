package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.quickbite.model.CartItemModel;
import com.quickbite.model.UserModel;
import com.quickbite.service.CartService;

@WebServlet(asyncSupported = true, urlPatterns = { "/checkout" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CartService cartService = new CartService();
	
    public CheckoutController() {
        super();

    }

    @Override
    /**
     * Handles GET requests for populating the cart data and its subtotal
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// fetch cart items using service layer
		Map<String, List<CartItemModel>> groupedCart = cartService.getGroupedCart(session);
		List<CartItemModel> flatCart = cartService.getCart(session);
	    
		// calculations
		double subtotal = cartService.calculateSubtotal(flatCart);

		
		// set attributes
		request.setAttribute("groupedCart", groupedCart);
		request.setAttribute("subtotal", subtotal);
		
		request.getRequestDispatcher("/WEB-INF/views/customer/checkout.jsp").forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// capture form data from jsp
		String deliveryTimeType = request.getParameter("deliveryTime"); // asap or schedule
		String deliveryDate = request.getParameter("deliveryDate"); // YYYY-MM-DD
		String timeSlot = request.getParameter("deliveryTimeSlot"); // HH:MM
	    String specialInstructions = request.getParameter("specialInstructions");
	   
	    // get cart and user
	    List<CartItemModel> cart = cartService.getCart(session);
	    UserModel user = (UserModel) session.getAttribute("user");
	    
	    try {
	    	boolean success = cartService.processOrder(user, cart, deliveryTimeType, deliveryDate, timeSlot, specialInstructions);
	    	
	    	if (success) {
	    		session.removeAttribute("cart");
	    		response.sendRedirect(request.getContextPath()+"/home?orderStatus=success");
	    	} else {
	    		request.setAttribute("error", "Could not proccess order. Please try again.");
	    		doGet(request, response);
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	response.sendError(500, "Internal error during checkout.");
	    }
    }
		
}
