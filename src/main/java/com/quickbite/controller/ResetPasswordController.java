package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.model.UserModel;
import com.quickbite.service.ResetPasswordService;

/**
 * Servlet implementation class ResetPasswordController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/resetPassword" })
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ResetPasswordService resetService = new ResetPasswordService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET request by forwarding the user to the reset password form
	 * @since 2026-05-14
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/resetPassword.jsp").forward(request, response);
	}

	/**
	 * Handles POST request submitted from the reset password form
	 * 
	 * Validates that both email and phone number fields are not empty then
	 * calls required service functions for further tasks
	 * @param request The HTTP request containing email and number
	 * @param response The HTTP response object 
	 * @throws ServletException If the request dispatcher fails
	 * @see ResetPasswordService validateUser and requestPasswordReset Function
	 * @since 2026-05-14
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String number = request.getParameter("number");

        // Basic empty field check
        if (email == null || email.trim().isEmpty() ||
            number == null || number.trim().isEmpty()) {
            request.setAttribute("error", "Please fill in all fields.");
            request.getRequestDispatcher("/WEB-INF/views/customer/resetPassword.jsp").forward(request, response);
            return;
        }
        
        UserModel user = resetService.validateUser(email, number);
        
        if (user == null) {
            request.setAttribute("error", "No account found with those details.");
            request.getRequestDispatcher("/WEB-INF/views/customer/resetPassword.jsp").forward(request, response);
            return;
        }
        
        boolean success = resetService.requestPasswordReset(user.getUserId());
        
        if (success) {
            request.setAttribute("success", "Reset request submitted. An admin will update your password shortly.");
            request.getRequestDispatcher("/WEB-INF/views/customer/resetPassword.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/customer/resetPassword.jsp").forward(request, response);
        }
	}

}
