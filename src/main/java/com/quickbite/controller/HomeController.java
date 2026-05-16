package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.quickbite.dao.ItemDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
    }

    /**
	 * Handles HTTP GET requests to compile and render the public landing homepage.
	 * 
	 * Invokes ItemDAO to retrieve a list of popular menu items alongside their associated outlet details. 
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDAO itemDAO = new ItemDAO();
		List<Map<String, Object>> popularItems = itemDAO.getPopularItemsWithOutlets();
		request.setAttribute("itemList", popularItems);
		request.getRequestDispatcher("/WEB-INF/views/public/home.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests by delegating processing logic to the doGet method.
	 *
	 * @param request  
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException     
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
