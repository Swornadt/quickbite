package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.quickbite.dao.UserOutletDAO;
import com.quickbite.model.UserModel;
import com.quickbite.service.LoginService;
import com.quickbite.service.RegisterService;
import com.quickbite.utils.ImageUtil;
import com.quickbite.utils.SessionUtil;
import com.quickbite.utils.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login","/logout", "register" })
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		switch(endpoint) {
			case "/login":
				request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
				break;
			case "/register":
				request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
				break;
			default:
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		switch(endpoint) {
			case "/login":
				handleLogin(request, response);
				break;
			case "/logout":
				handleLogout(request, response);
				break;
			case "/register":
				handleRegister(request, response);
				break;
			default:
		}
	}

	private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reading all form fields 
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String number= request.getParameter("number");
		        String email= request.getParameter("email");
		        String gender= request.getParameter("gender");
		        String dob= request.getParameter("dob");
		        String newpass= request.getParameter("newpass");
		        String confirmpass= request.getParameter("confirmpass");
		        String terms= request.getParameter("terms");
		        
		        // Perform Validation
				String error = ValidationUtil.validateRegistration(fname,
						lname, number, email, dob, newpass, confirmpass, terms);
				
				// if validation failed
				if (error != null) {
					request.setAttribute("error", error);
					
					request.setAttribute("fname", fname);
					request.setAttribute("lname", lname);
					request.setAttribute("number", number);
					request.setAttribute("email", email);
					
					request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
					return;
				}
		        
				// once every validation succeeds
		        try {
		        	//Image Upload
			        ImageUtil imageUtil = new ImageUtil();
			        Part filePart = request.getPart("image");
			        String imagePath = imageUtil.uploadProfileImage(filePart, "uploads", getServletContext());
			        
			        // calls to service to save to DB
		        	RegisterService service = new RegisterService();
		        	service.registerUser(fname, lname, number, email, gender, dob, newpass, imagePath);
		        	
		        	request.setAttribute("success", "Registration successful!");
		        	request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
		        	
		        }catch(Exception e) {
		        	//This shows error message
		        	e.printStackTrace();
		        	request.setAttribute("error", "Something went wrong. Please try again.");
		            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
		                   .forward(request, response);
		        }
			
		
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionUtil.invalidateSession(request);
		response.sendRedirect(request.getContextPath()+"/login");
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	if (user.getStatus().equalsIgnoreCase("pending")) {
        		request.setAttribute("error", "Your account is in pending status and requires admin approval");
        		request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
        		return;
        	}
        	
        	//When user status is rejected
        	if (user.getStatus().equalsIgnoreCase("rejected")) {
        		request.setAttribute("error", "Your account has been rejected. Please contact support.");
        		request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request,response);
        		return;
        	}
        	
        	//When user status is active
        	SessionUtil.setAttribute(request, "user", user);
        	request.setAttribute("success","Login successful!");
        	
        	//Redirection based on role
        	String targetPath;
        	switch (user.getRole().toLowerCase()) {
        		case "admin":
        			targetPath = "/admin/customers";
        			break;
        		case "staff":
        			int outletId = new UserOutletDAO().getOutletByUser(user.getUserId());
        			request.getSession().setAttribute("outletId", outletId);
        			targetPath = "/kitchen";
        		default:
        			targetPath = "/home";
        	}
        	response.sendRedirect(request.getContextPath() + targetPath);
        } else {
        	request.setAttribute("error", "Invalid phone number or password.");
        	request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
	}

}
