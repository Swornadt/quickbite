package com.quickbite.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.quickbite.dao.FeedbackDAO;
import com.quickbite.model.FeedbackModel;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;
import com.quickbite.model.UserModel;
import com.quickbite.service.MenuService;
import com.quickbite.service.AdminCustomerService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/*" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Servlet communicates with DAO through service
    private AdminCustomerService adminCustomerService = new AdminCustomerService();

    public AdminController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		
		if (path==null || path.equals("/")) {
			showDashboard(request, response);
			return;
		}
		
		switch (path) {
			case "/menu":
				showMenuManagement(request, response);
				break;
			case "/customers":
				viewCustomers(request, response);
				break;
			case "/feedback":
				viewFeedback(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}

	
	private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-main-dashboard.jsp").forward(request,response);
	}
	
	private void showMenuManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuService menuService = new MenuService();

        String outletIdStr = request.getParameter("outletId");

        // Get data through Service
        List<Outlet> outlets = menuService.getAllOutlets();
        List<OutletItem> outletItems = menuService.getMenuItems(outletIdStr);

        // Set attributes
        request.setAttribute("outletItems", outletItems);
        request.setAttribute("outlets", outlets);
        request.setAttribute("selectedOutletId", outletIdStr);

        request.getRequestDispatcher("/WEB-INF/views/admin/admin-menu-view.jsp").forward(request, response);
	}
	
	/**
	 * doGet() runs when the browser visits /admin/customers
	 * It fetches all pending users from the DB and sends them to the JSP
	 */
	private void viewCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<UserModel> pendingUsers = adminCustomerService.getPendingUsers();
		List<UserModel> activeCustomers = adminCustomerService.getActiveCustomers();
		
		
		//Attaching the list to the request so the jsp can access it
		request.setAttribute("pendingUsers",pendingUsers);
		request.setAttribute("activeCustomers",activeCustomers);
		
		//Foward to JSP, the JSP wil loop through the list and display each user
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCustomerApproval.jsp").forward(request,response);
	}

	
	private void viewFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FeedbackDAO feedbackDao = new FeedbackDAO();
			List<FeedbackModel> feedbackList = feedbackDao.getAllFeedbacks();
			
			// Calculate total, average rating, and rating distribution count
            int totalFeedback = feedbackList.size();
            double avgRating = 0.0;
            int[] ratingCount = new int[6];
            
            if (totalFeedback > 0) {
                int sum = 0;
                for (FeedbackModel fb : feedbackList) {
                    sum += fb.getRatingValue();
                    if (fb.getRatingValue() >= 1 && fb.getRatingValue() <= 5) {
                        ratingCount[fb.getRatingValue()]++;
                    }
                }
                avgRating = (double) sum / totalFeedback;
            }
            
            // Round to 1 decimal place
            avgRating = Math.round(avgRating * 10.0) / 10.0;

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("totalFeedback", totalFeedback);
            request.setAttribute("avgRating", avgRating);
            request.setAttribute("ratingCount", ratingCount);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load feedbacks.");
		}
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-feedback.jsp").forward(request,response);
		
	}
	
	
	/**
	 * doPost() runs when the Approve or Reject button is clicked
	 * It reads user_id and action from the form, updates the DB, then redirect back
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		
		switch (path) {
			case "/customers/update":
				handleCustomerStatus(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	private void handleCustomerStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
