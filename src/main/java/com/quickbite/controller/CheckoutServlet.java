package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.quickbite.model.CartItemModel;
import com.quickbite.model.UserModel;
import com.quickbite.service.CartService;

@WebServlet(asyncSupported = true, urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CartService cartService = new CartService();
	
    public CheckoutServlet() {
        super();

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// fetch cart items using service layer
		List<CartItemModel> cartItems = cartService.getCart(session);
		
		// calculations
		double subtotal = 0;
		if (cartItems != null) {
			subtotal = cartItems.stream().mapToDouble(CartItemModel::getTotalPrice).sum();
		}
		
		// set attributes
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("subtotal", subtotal);
		request.setAttribute("locationOfFood", "Kumari Cafe"); //TODO: send cafe location from dao/session
		
		request.getRequestDispatcher("/WEB-INF/views/customer/checkout.jsp").forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		// capture form data from jsp
		String deliveryTimeType = request.getParameter("deliveryTime"); // asap or schedule
		String deliveryDate = request.getParameter("deliveryDate");
		String timeSlot = request.getParameter("deliveryTimeSlot");
	    String specialInstructions = request.getParameter("specialInstructions");
	    
	    // get cart and user
	    List<CartItemModel> cart = cartService.getCart(session);
	    UserModel user = (UserModel) session.getAttribute("user");
	    
	    if (cart == null || cart.isEmpty()) {
	    	response.sendRedirect(request.getContextPath()+"/views/customer/outlets");
	    	return;
	    }
	    
	    // TODO: call orderservice to save to DB
	    try {
	    	boolean success = cartService.placeOrder(user.getFname(), cart, specialInstructions); //pass correct params
	    	
	    	if (success) {
	    		session.removeAttribute("cart");
	    		response.sendRedirect(request.getContextPath()+"/order-success"); //TODO: put correct path
	    	} else {
	    		request.setAttribute("error", "Could not proccess order. Please try again.");
	    		doGet(request, response);
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
    }
		
}
