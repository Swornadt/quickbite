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
@WebServlet(asyncSupported = true, urlPatterns = { "/cart" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
