package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalDate;

import com.quickbite.service.RegisterService;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
        
        
        //Checking for empty values
        if (fname == null || fname.trim().isEmpty() ||
                lname == null || lname.trim().isEmpty() ||
                number == null || number.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                gender == null || gender.trim().isEmpty() ||
                dob == null || dob.trim().isEmpty() ||
                newpass == null || newpass.trim().isEmpty() ||
                confirmpass == null || confirmpass.trim().isEmpty()) {
        	request.setAttribute("error", "All fields are required.");
        	request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
        	return;
        }
        
        //First Name validation
        if (!fname.matches("[a-zA-Z ]+")) {
            request.setAttribute("error", "First name must contain letters only.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        //Second Name validation
        if (!lname.matches("[a-zA-Z ]+")) {
            request.setAttribute("error", "Last name must contain letters only.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        } 
        
        //Phone Number length validation
        if ( number.length() != 10) {
            request.setAttribute("error", "Phone number must be 10 characters (e.g. 9812345678).");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        //Email validation
        if (!email.contains("@gmail.com")) {
            request.setAttribute("error", "Email address must contain '@gmail.com'");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        //DOB validation
        try {
            LocalDate dobDate = LocalDate.parse(dob);
            if (!dobDate.isBefore(LocalDate.now())) {
                request.setAttribute("error", "Date of birth must be in the past.");
                request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        //Password Validation
        if (newpass.length() <= 6 ||
                !newpass.matches(".*[A-Z].*") ||
                !newpass.matches(".*[0-9].*") ||
                !newpass.matches(".*[!@#$%^&*].*")) {

                request.setAttribute("error", "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).");
                request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
                return;
            }
        
        //Confirm password matching new password validation
        if (!newpass.equals(confirmpass)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        //Terms must be checked
        if (terms == null) {
            request.setAttribute("error", "You must agree to the Terms of Use.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request,response);
            return;
        }
        
        try {
        	RegisterService service = new RegisterService();
        	service.registerUser(fname, lname, number, email, gender, dob, newpass);
        	System.out.println("Registartion successful for:" + fname + " " + lname);
        	request.setAttribute("success", "Registration successful!");
        	request.getRequestDispatcher("/WEB-INF/views/public/home.jsp").forward(request,response);
        }catch(Exception e) {
        	//This shows error message
        	e.printStackTrace();
        	request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                   .forward(request, response);
        }
	}
	

}
