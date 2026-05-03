package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.model.UserModel;
import com.quickbite.service.AdminService;
import com.quickbite.utils.SessionUtil;

/**
 * Servlet implementation class AdminProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-profile" })
public class AdminProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserModel sessionUser = (UserModel) SessionUtil.getAttribute(request, "user");
		
		if (sessionUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		//Fetching the admin with Id 1 for now, will have to replace with session ID later
		int currentId = sessionUser.getUserId();

		// 1. Initializing the service
		AdminService adminService = new AdminService();

		UserModel admin = adminService.getUserById(currentId);
		
		request.setAttribute("userData", admin);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Getting the Parameter as a String first
		String userIdStr = request.getParameter("user_id");
		
		if(userIdStr == null || userIdStr.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/admin-profile?update=error1");
			return;
		}
		try {
			//Extracting from data
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String number = request.getParameter("number");
					
			//Update via service
			AdminService adminService = new AdminService();
			boolean success = adminService.updateAdminProfile(user_id, fname, lname, email, number);
			
			if(success) {
				response.sendRedirect(request.getContextPath() + "/admin-profile?update=success");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/admin-profile?update=fail");
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/admin-profile?update=error2");
		}
	}

}
