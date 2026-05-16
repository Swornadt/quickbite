package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    /**
     * Handles GET requests for populating the cart data and its subtotal
     * 
     * It verifies the user session, retrieves current shopping cart grouped by outlet,
     * and calculates the total order cost. This is then forwarded to the checkout jsp 
     * for rendering.
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     */
    @Override
    /**
     * Handles GET requests for populating the cart data and its subtotal
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     */
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
		HttpSession session = request.getSession(false);
	    
	    // fetching the data using service
	    List<CartItemModel> flatCart = cartService.getCart(session);
	    double subtotal = cartService.calculateSubtotal(flatCart);

	    // mapping the data to the attributes the JSP expects
	    request.setAttribute("flatCart", flatCart);
	    request.setAttribute("subtotal", subtotal);
	    
		request.getRequestDispatcher("/WEB-INF/views/customer/payment.jsp").forward(request, response);
	}
  
    /**
     * Handles the POST request for submitting the checkout form and initiates order placement
     * 
     * It extracts the preferences for order placememnt (asap or scheduled) from the request.
     * The business logic of order processing is delegated to CartService.
     * Upon success, the cart is cleared from session and user is redirected to confirmation view.
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String endpoint = request.getServletPath();
		
		if (session == null || session.getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}

		if ("/checkout".equals(endpoint)) {
			handleCheckoutSubmission(request, response, session);
		} else if ("/payment".equals(endpoint)) {
			handleFinalPlacement(request, response, session);
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
	        if (deliveryDate == null || deliveryDate.isEmpty() || timeSlot == null || timeSlot.isEmpty()) {
	            request.setAttribute("error", "Please select both a date and time slot for scheduled orders.");
	            viewCheckout(request, response);
	            return;
	        }
	        
	        // Validation to prevent scheduling in the past
	        try {
	            LocalDate parsedDate = LocalDate.parse(deliveryDate);
	            LocalTime parsedTime = LocalTime.parse(timeSlot);
	            LocalDateTime scheduledDateTime = LocalDateTime.of(parsedDate, parsedTime);
	            LocalDateTime now = LocalDateTime.now();
	            
	            if (scheduledDateTime.isBefore(now)) {
	                request.setAttribute("error", "The scheduled time cannot be in the past. Please select a future time.");
	                viewCheckout(request, response);
	                return;
	            }
	        } catch (java.time.format.DateTimeParseException e) {
	            request.setAttribute("error", "Invalid date or time format provided.");
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
	    session.setAttribute("pending_type", deliveryTimeType);
	    session.setAttribute("pending_date", deliveryDate);
	    session.setAttribute("pending_slot", timeSlot);
	    session.setAttribute("pending_notes", specialInstructions);
			        
		// redirect to payment
		response.sendRedirect(request.getContextPath() + "/payment");
	}
	
	private void handleFinalPlacement(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		UserModel user = (UserModel) session.getAttribute("user");
		List<CartItemModel> cart = cartService.getCart(session);
		
		// receive the data in session
		String type = (String) session.getAttribute("pending_type");
		String date = (String) session.getAttribute("pending_date");
		String slot = (String) session.getAttribute("pending_slot");
		String notes = (String) session.getAttribute("pending_notes");
	    
	    try {
	    	boolean success = cartService.processOrder(user, cart, type, date, slot, notes);
	    	
	    	if (success) {
	    		// cleanup the session
	    		session.removeAttribute("cart");
	            session.removeAttribute("pending_type");
	            session.removeAttribute("pending_date");
	            session.removeAttribute("pending_slot");
	            session.removeAttribute("pending_notes");
	            response.sendRedirect(request.getContextPath() + "/home?orderStatus=success");
	    	} else {
	    		response.sendRedirect(request.getContextPath()+"/payment?error=fail_to_place");
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	response.sendError(500, "Internal error during order placement.");
	    }
	}
		
}
