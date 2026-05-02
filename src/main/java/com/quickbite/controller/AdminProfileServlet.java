package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.quickbite.model.AdminModel;
import com.quickbite.service.AdminService;

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
		
		// 1. Initializing the service
		AdminService adminService = new AdminService();
		
		//Fetching the admin with Id 1 for now, will have to replace with session ID later
		AdminModel admin = adminService.getAdminById(1);
		
		request.setAttribute("adminData", admin);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
