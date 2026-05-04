package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.quickbite.dao.UserOutletDAO;
import com.quickbite.model.UserModel;
import com.quickbite.service.LoginService;
import com.quickbite.utils.CookieUtil;
import com.quickbite.utils.SessionUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
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
		request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
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
		  request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
		  return;
		}
		
		//Phone number length validation
		if (number.trim().length() != 10) {
            request.setAttribute("error", "Phone number must be 10 characters (e.g. 9812345678).");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }
		
		 //Password Validation
        if (pass.length() <= 6 ||
                !pass.matches(".*[A-Z].*") ||
                !pass.matches(".*[0-9].*") ||
                !pass.matches(".*[!@#$%^&*].*")) {

                request.setAttribute("error", "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                return;
            }
        
		// Password Verification
        LoginService loginService = new LoginService();
        UserModel user = loginService.authenticate(number, pass);
        
        if (user != null) {   
        	
        	//When user status is pending
        	if (user.getStatus().equals("pending")) {
        		request.setAttribute("error", "Your account is in pending status and requires admin approval");
        		request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
        		return;
        	}
        	
        	//When user status is rejected
        	if (user.getStatus().equals("rejected")) {
        		request.setAttribute("error", "Your account has been rejected. Please contact support.");
        		request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
        		return;
        	}
        	
        	//When user status is active
        	SessionUtil.setAttribute(request, "user", user);
        	request.setAttribute("success","Login successful!");
        	
        	//Redirection based on role
        	if (user.getRole().equals("admin")) {
        		response.sendRedirect(request.getContextPath() + "/admin/customers");
        	} else if (user.getRole().equals("staff")) {
        		UserOutletDAO userOutletDAO = new UserOutletDAO();
        	    int outletId = userOutletDAO.getOutletByUser(user.getUserId());
        	    request.getSession().setAttribute("outletId", outletId);
        	    response.sendRedirect(request.getContextPath() + "/kitchen");
        	}else {
        		response.sendRedirect(request.getContextPath() + "/home");
        	}
        } else {
        	request.setAttribute("error", "Invalid phone number or password.");
        	request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
	}

}
