package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/about","/about/*" })
public class AboutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AboutController() {
        super();
    }

    /**
     * Handles the GET request by routing to display each member about page
     * 
     * Based on the URL path, the method dispatches the request
     * to each individual's about pages.
     * 
* @param request containing client request and path info details.
	 * @param response for rendering the target JSP view or returning error statuses.
	 * @throws ServletException if a target JSP file encounters a compilation or runtime error during dispatching.
	 * @throws IOException if an input or output error is detected when the container forwards the request or sends an error code.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String endpoint = request.getPathInfo();
		
		// for /about or /about/
		if (endpoint == null || endpoint.equals("/")) {
	        request.getRequestDispatcher("/WEB-INF/views/public/about-page.jsp").forward(request, response);
	        return;
	    }
		
		// routing on the basis of which endpoint is hit
		switch (endpoint) {
			case "/hridishna":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/hridishna.jsp").forward(request, response);
		        break;
		        
		    case "/resha":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/resha.jsp").forward(request, response);
		        break;
		        
		    case "/sabrina":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/sabrina.jsp").forward(request, response);
		        break;
		        
		    case "/sahil":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/sahil.jsp").forward(request, response);
		        break;
		        
		    case "/sanskar":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/sanskar.jsp").forward(request, response);
		        break;
		        
		    case "/sworna":
		        request.getRequestDispatcher("/WEB-INF/views/public/about/sworna.jsp").forward(request, response);
		        break;
		
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}
	
	/**
     * Handles the GET request by routing to display each member about page
     * 
* @param request containing client request context.
	 * @param response for rendering the target JSP view via the shared doGet handler.
	 * @throws ServletException if the underlying JSP throws a server-side exception.
	 * @throws IOException if an input or output system failure occurs while routing or rendering the response view.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
