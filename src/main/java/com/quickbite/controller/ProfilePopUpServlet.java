package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.quickbite.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/ProfilePopUpServlet" })
public class ProfilePopUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}