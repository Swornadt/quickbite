package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/pages/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = request.getParameter("number");
		String pass = request.getParameter("pass");
		
		//Checking for empty value in Number and password input field
		if (number == null || number.trim().isEmpty() || pass == null|| pass.trim().isEmpty()) {
		  request.setAttribute("error", "Phone number and password are required");
		  request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		  return;
		}
		
		//Phone number length validation
		if (number.trim().length() != 10) {
            request.setAttribute("error", "Phone number must be 10 characters (e.g. 9812345678).");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            return;
        }
		
		 //Password Validation
        if (pass.length() <= 6 ||
                !pass.matches(".*[A-Z].*") ||
                !pass.matches(".*[0-9].*") ||
                !pass.matches(".*[!@#$%^&*].*")) {

                request.setAttribute("error", "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).");
                request.getRequestDispatcher("pages/login.jsp").forward(request, response);
                return;
            }
        
		
		request.setAttribute("success","Login successful!");
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

}
