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

@WebServlet(asyncSupported = true, urlPatterns = { "/checkout", "/payment" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CartService cartService = new CartService();
	
    public CheckoutController() {
        super();

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String endpoint = request.getServletPath();
    	if ("/checkout".equals(endpoint)) {
            viewCheckout(request, response);
        } else if ("/payment".equals(endpoint)) {
            viewPayment(request, response);
        } else {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND);
    	}
	}

	private void viewCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	
	private void viewPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/payment.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String endpoint = request.getServletPath();
		
		if (session == null || session.getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("User session null! checkoutservlet");
			return;
		}
		
		if ("/checkout".equals(endpoint)) {
			handleCheckoutSubmission(request, response, session);
		} else if ("/payment".equals(endpoint)) {
			handleFinalPlacement(request, response, session);
		}
	    
	    

	    
	    // get cart and user
	    List<CartItemModel> cart = cartService.getCart(session);
	    UserModel user = (UserModel) session.getAttribute("user");
	    
	    if (cart == null || cart.isEmpty()) {
	    	response.sendRedirect(request.getContextPath()+"/outlets");
	    	return;
	    }
	    
	    try {
	    	boolean success = cartService.placeOrder(user, cart, specialInstructions, finalPreferredDate);
	    	
	    	if (success) {
	    		session.removeAttribute("cart");
	    		response.sendRedirect(request.getContextPath()+"/home?orderStatus=success");
	    	} else {
	    		request.setAttribute("error", "Could not proccess order. Please try again.");
	    		doGet(request, response);
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
    }

	private void handleCheckoutSubmission(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException {
		// get data from the frontend
		String deliveryTimeType = request.getParameter("deliveryTime"); // asap or schedule
		String deliveryDate = request.getParameter("deliveryDate"); // YYYY-MM-DD
		String timeSlot = request.getParameter("deliveryTimeSlot"); // HH:MM
		String specialInstructions = request.getParameter("specialInstructions");
		
		// validate scheduled orders
		if ("later".equals(deliveryTimeType)) {
	    	if (deliveryDate != null && !deliveryDate.isEmpty() && timeSlot != null && !timeSlot.isEmpty()) {
	    		request.setAttribute("error", "Please select both a date and time slot for scheduled orders.");
	    		viewCheckout(request, response);
	    		return;
	    	}
	    } 
		
		// validate empty carts
		List<CartItemModel> cart = cartService.getCart(session);
	    if (cart == null || cart.isEmpty()) {
	        response.sendRedirect(request.getContextPath() + "/outlets");
	        return;
	    }
	    
	    
		
		// store it in session
		session.setAttribute("tempDeliveryType", deliveryTimeType);
		session.setAttribute("tempDate", deliveryDate);
		session.setAttribute("tempTime", timeSlot);
		session.setAttribute("tempInstructions", specialInstructions);
			        
		// redirect to payment
		response.sendRedirect(request.getContextPath() + "/payment");
	}
	
	private void handleFinalPlacement(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("user");
		List<CartItemModel> cart = cartService.getCart(session);
		
		// receive the data in session
		String type = (String) session.getAttribute("pendingTimeType");
	    String date = (String) session.getAttribute("pendingDate");
	    String slot = (String) session.getAttribute("pendingSlot");
	    String notes = (String) session.getAttribute("pendingNotes");
	    
	    try {
	    	boolean success = cartService.placeOrder(user, cart, type, date, slot, notes);
	    }
	}
		
}
