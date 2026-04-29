package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.model.UserModel;
import com.quickbite.dao.UserDAO;
import com.quickbite.service.AdminCustomerService;
import java.util.List;

/**
 * Servlet implementation class AdminCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/customers" })
public class AdminCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    //Servlet communicates with DAO through service
    private AdminCustomerService adminCustomerService = new AdminCustomerService();
    
	/**
	 * doGet() runs when the browser visits /admin/customers
	 * It fetches all pending users from the DB and sends them to the JSP
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<UserModel> pendingUsers = adminCustomerService.getPendingUsers();
		List<UserModel> activeCustomers = adminCustomerService.getActiveCustomers();
		
		
		//Attaching the list to the request so the jsp can access it
		request.setAttribute("pendingUsers",pendingUsers);
		request.setAttribute("activeCustomers",activeCustomers);
		
		//Foward to JSP, the JSP wil loop through the list and display each user
		request.getRequestDispatcher("/WEB-INF/views/admin/tempCustomerApproval.jsp").forward(request,response);
		
	}

	/**
	 * doPost() runs when the Approve or Reject button is clicked
	 * It reads user_id and action from the form, updates the DB, then redirect back
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads the two values the form send
		//user_id - which user to update
		//action - "approve" or "reject"
		
		String userIdParam = request.getParameter("user_id");
		String action = request.getParameter("action");
		
		adminCustomerService.updateUserStatus(userIdParam,action);
		
		//Always redirect back after a POST - prevent resubmission on refresh
		response.sendRedirect(request.getContextPath() + "/admin/customers");
		
	}

}
