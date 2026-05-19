package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/contact"})
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @param request containing client request configurations and attributes.
	 * @param response for rendering the target contact JSP view page.
	 * @throws ServletException if the underlying contact template engine encounters a compilation or runtime error.
	 * @throws IOException if an error occurs while the container streams data to the client view layout.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request,response);
	}

	/**
	 * @param request containing form parameter fields or submission states.
	 * @param response for rendering the target contact view via the shared doGet handler.
	 * @throws ServletException if the shared handler or underlying JSP throws a server-side exception.
	 * @throws IOException if a communication system failure occurs during routing or rendering the response view.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
