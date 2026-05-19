package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.quickbite.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/profile-popup" })
public class ProfilePopupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
	 * Handles HTTP GET requests to retrieve and render the customer's profile micro-dashboard context.
	 * 
	 * Evaluates the active session state without initializing a new session block to confirm that the requesting client is properly authenticated. 
	 * If no session or user identity data is detected, it triggers a redirection back to the login interface. 
	 * Upon successful verification, it extracts the current user model details from the session context, attaches it to the request under the attribute "user", and dispatches execution 
	 * to the profile model view template located at "/WEB-INF/views/customer/profile-pop-up.jsp".
	 * 
	 * @param request the HTTP request holding active customer data attributes.
	 * @param response the HTTP response used to handle redirects or load the small popup layout.
	 * @throws ServletException if a problem is encountered while dispatching to the popup view.
	 * @throws IOException if a network connection interruption occurs.
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);  // false = don't create new session
        
        // Safety check: If no session or no user logged in → redirect to login
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        
        // Pass user object to JSP
        request.setAttribute("user", user);
        
        // Forward to profile popup page
        request.getRequestDispatcher("/WEB-INF/views/customer/profile-pop-up.jsp")
               .forward(request, response);
    }

    /**
	 * Handles HTTP POST requests by delegating processing logic to the doGet method.
	 * 
	 * @param request the HTTP request container details sent by the client browser.
	 * @param response the HTTP response handler route used to send back response data.
	 * @throws ServletException if the internal server-side servlet router encounters an issue.
	 * @throws IOException if an error happens while streaming data back to the user's browser.
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}