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
import com.quickbite.service.CartService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/cart","/cart/*" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		HttpSession session = request.getSession();
		
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
	
	private void handleAddToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			// extract data
			int itemId = Integer.parseInt(request.getParameter("itemId"));
            int outletId = Integer.parseInt(request.getParameter("outletId"));
            String itemName = request.getParameter("itemName");
            double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String outletName = request.getParameter("outletName");
            
            // create model
            CartItemModel newItem = new CartItemModel(itemId, outletId, itemName, unitPrice, quantity);
            
            // update session via service
            HttpSession session = request.getSession(); 
            cartService.addToCart(session, newItem);
            
            // get source url
            String referer = request.getHeader("Referer");
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
	
	private void handleRemoveFromCart (HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			cartService.removeFromCart(request.getSession(), itemId);
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/cart?error=true");
		}
	}
	
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
