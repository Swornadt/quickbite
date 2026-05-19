package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.quickbite.dao.FeedbackDAO;
import com.quickbite.dao.ItemDAO;
import com.quickbite.dao.OrderDAO;
import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.dao.ReportDAO;
import com.quickbite.model.FeedbackModel;
import com.quickbite.model.ItemModel;
import com.quickbite.model.OrderModel;
import com.quickbite.model.OutletModel;
import com.quickbite.model.OutletItemModel;
import com.quickbite.model.ReportModel;
import com.quickbite.model.UserModel;
import com.quickbite.service.AdminService;
import com.quickbite.service.MenuService;
import com.quickbite.utils.ImageUtil;
import com.quickbite.utils.SessionUtil;
import com.quickbite.utils.ValidationUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(asyncSupported = true, urlPatterns = { "/admin", "/admin/*" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private AdminService adminService;

	
    @Override
    public void init() throws ServletException {
        // Initialize the service once when the servlet starts
    	this.adminService = new AdminService();
    }

	/**
	 * Handles GET requests by routing them to corresponding admin features.
	 * 
	 * Based on the URL path, the method dispatches the request
	 * to specialized handlers for:
	 * - Admin Dashboard
	 * - Menu Management (CRUD)
	 * - Customer Management (view, approvals, requests)
	 * - Feedback & Analytics
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 * @see #showDashboard(HttpServletRequest, HttpServletResponse)
	 * @see #showMenuManagement(HttpServletRequest, HttpServletResponse)
	 * @see #viewCustomers(HttpServletRequest, HttpServletResponse)
	 * @see #viewFeedback(HttpServletRequest, HttpServletResponse)
	 * @see #viewAdminProfile(HttpServletRequest, HttpServletResponse)
	 * @see #viewCustomerProfile(HttpServletRequest, HttpServletResponse)
	 * @see #viewMenuAdd(HttpServletRequest, HttpServletResponse)
	 * @see #viewMenuEdit(HttpServletRequest, HttpServletResponse)
	 * @see #viewMenuDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();

		if (path == null || path.equals("/")) {
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
			case "/profile":
				viewAdminProfile(request, response);
				break;
			case "/customers/profile":
				viewCustomerProfile(request, response);
				break;
			case "customers/reset":
				handleResetRequest(request,response);
				break;
			case "/menu/add":
				viewMenuAdd(request, response);
				break;
			case "/menu/edit":
				viewMenuEdit(request, response);
				break;
			case "/menu/delete":
				viewMenuDelete(request, response);
				break;
			case "/report":
				viewReport(request, response);
				break;
			case "/resetPassword":
				resetPassword(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}

	/**
	 * Dispatches to Admin Dashboard page
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException if the target JSP file encounters an error.
     * @throws IOException if an input or output error is detected during the request forward.
	 */
	private void showDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-main-dashboard.jsp").forward(request, response);
	}

	/**
	 * Displays Admin's profile.
	 * 
	 * Gets the session of the user currently in session and redirects to login if
	 * not available.
	 * Calls adminService to get user by Id and sets attribute 'userData'
	 * accordingly.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void viewAdminProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Gets today's date and sets it as maxDate
		LocalDate today = LocalDate.now();
		request.setAttribute("maxDate", today.toString());
		
		//Retrieves logged-in user object from the current session
		UserModel sessionUser = (UserModel) SessionUtil.getAttribute(request, "user");

		if (sessionUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int currentId = sessionUser.getUserId();

		//Initializing the service
		AdminService adminService = new AdminService();

		UserModel admin = adminService.getUserById(currentId);
		
		request.setAttribute("user", admin);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request,response);
	}

	/**
	 * Displays Customer's profile
	 * 
	 * Gets the user's Id from request and then instantiates a user model
	 * using that Id. The attribute of that customers's data is forwarded to the view.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void viewCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//Gets the id from the URL
				String userIdParam = request.getParameter("userId");
				if (userIdParam !=null) {
					try {
						//Parsing the id from String to Integer
						int userId = Integer.parseInt(userIdParam);		
						AdminService adminService = new AdminService();
						OrderDAO orderDAO = new OrderDAO();
						
						//Retrieving User Data
						UserModel user = adminService.getUserById(userId);			
						if(user !=null) {
							request.setAttribute("customerData", user);
						}
						
						//Retrieving split order history (Current and Past Orders)
						List<OrderModel> currentOrders = orderDAO.getOrdersByStatus(userId, true);
						List<OrderModel> pastOrders = orderDAO.getOrdersByStatus(userId, false);
						
						request.setAttribute("currentOrders", currentOrders);
						request.setAttribute("pastOrders", pastOrders);
					}
					catch(NumberFormatException e) {
						e.printStackTrace();
					}
				
				}
				
				
				request.getRequestDispatcher("/WEB-INF/views/admin/customer-profile.jsp").forward(request,response);
	}
	
	/**
     * Handles the menu management display for admins
     * 
     * Retrieves selected outlet ID from request parameter or session fallback,
     * fetches all outlets and menu items for the selected outlet, then forwards
     * the request to the admin menu view JSP page.
     * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
     */
	private void showMenuManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuService menuService = new MenuService();

		String outletParam = request.getParameter("outletId");
		Long selectedOutletId = 0L;

		if (outletParam != null && !outletParam.isEmpty() && !outletParam.equals("null")) {
			try {
				selectedOutletId = Long.parseLong(outletParam.trim());
			} catch (NumberFormatException e) {
				// Log the error and fall back to 0 or session
				selectedOutletId = 0L;
			}
		} else {
			// Fallback to Session if the parameter is missing
			Object sessionOutlet = request.getSession().getAttribute("selectedOutletId");
			if (sessionOutlet instanceof Long) {
				selectedOutletId = (Long) sessionOutlet;
			}
		}

		// Get data through Service
		List<OutletModel> outlets = menuService.getAllOutlets();
		List<OutletItemModel> outletItems = menuService.getMenuItems(outletParam);

		// Set attributes
		request.setAttribute("outletItems", outletItems);
		request.setAttribute("outlets", outlets);
		request.setAttribute("selectedOutletId", selectedOutletId);

		request.getRequestDispatcher("/WEB-INF/views/admin/admin-menu-view.jsp").forward(request, response);
	}

	/**
	 * doGet() runs when the browser visits /admin/customers
	 * It fetches all pending, active and passwordResetRequest users from the DB and sends them to the JSP
	 */
	private void viewCustomers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print("Hit viewCustomers");

		List<UserModel> pendingUsers = adminService.getPendingUsers();
		List<UserModel> activeCustomers = adminService.getActiveCustomers();
		List<UserModel> passwordResetRequests = adminService.getPasswordResetRequests();
		
		
		//Attaching the list to the request so the jsp can access it
		request.setAttribute("pendingUsers",pendingUsers);
		request.setAttribute("activeCustomers",activeCustomers);
		request.setAttribute("passwordResetRequests", passwordResetRequests);
		
		//Foward to JSP, the JSP wil loop through the list and display each user
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-customer-approval.jsp").forward(request,response);
	}

	/**
	 * Loads and displays feedback management page for admins.
	 * 
	 * Retrieves all feedback records from FeedbackDAO, calculates the
	 * total feedback count, average rating, and rating distribution counts, sets those values 
	 * as request attributes, and forwards to admin feedback JSP.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException if the target JSP file encounters an error.
     * @throws IOException if an input or output error is detected during the request forward.
	 */
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
					int rating = fb.getRatingValue();
					sum += rating;
					if (rating >= 1 && rating <= 5) {
						ratingCount[rating]++;
					}
				}
				avgRating = (double) sum / totalFeedback;
			}

			// Round to 1 decimal place
			avgRating = Math.round(avgRating * 10.0) / 10.0;
			
			//Calculating filled star
			int filledStars = (int) Math.round(avgRating);

			request.setAttribute("feedbackList", feedbackList);
			request.setAttribute("totalFeedback", totalFeedback);
			request.setAttribute("avgRating", avgRating);
			request.setAttribute("ratingCount", ratingCount);
			request.setAttribute("filledStars", filledStars);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to load feedbacks.");
		}
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-feedback.jsp").forward(request, response);

	}

	/**
	 * Prepares data required to render the add-menu-item page and forwards it to view.
	 * 
	 * Loads the list of outlets from OutletDAO and places it in the request as the outlets attribute, 
	 * then forwards to the admin add-intem JSP.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException if the target JSP file encounters a compilation or runtime error.
     * @throws IOException if an input or output error is detected during the request forward.
	 */
	private void viewMenuAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		loadOutlets(request);
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(request, response);
	}
	
	/**
	 * Routes to the delete-menu-item page for admin users.
	 * 
	 * Forwards the request to the admin delete-item JSP. This method does not 
	 * perform any data loading; the JSP handles retrieval of items to delete.
	 * 
     * @param request containing client request data.
     * @param response for rendering the view response.
     * @throws ServletException if the target JSP file encounters a compilation or runtime error.
     * @throws IOException if an input or output error occurs during the request forward.
	 */
	private void viewMenuDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-delete-item.jsp").forward(request, response);
	}

	/**
	 * Loads item and outlet data for editing a menu items and forwards to the edit page.
	 * 
	 * Reads the itemId, requests parameter and attempts to parse it as an interger.
	 * If parsing succeeds, the method loads the Item using ItemDAO and places it in the request as item.
	 * The method also loads all outlets and sets them as the outlets attribute.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void viewMenuEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIdStr = request.getParameter("itemId");
		String outletIdStr = request.getParameter("outletId"); // Important: Get from URL

		if (itemIdStr == null || itemIdStr.trim().isEmpty()) {
			request.setAttribute("message", "Item ID is missing!");
			loadOutlets(request);
			request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
			return;
		}

		try {
			int itemId = Integer.parseInt(itemIdStr.trim());
			int outletId = 0;

			if (outletIdStr != null && !outletIdStr.trim().isEmpty()) {
				outletId = Integer.parseInt(outletIdStr.trim());
			}

			ItemDAO itemDAO = new ItemDAO();
			ItemModel item = itemDAO.getItemById(itemId);
			if (item != null) {
				request.setAttribute("item", item);
			}

			// Load specific outlet + price for this item
			if (outletId > 0) {
				OutletItemDAO outletItemDAO = new OutletItemDAO();
				OutletItemModel current = outletItemDAO.getOutletItemByItemAndOutlet(itemId, outletId);
				if (current != null) {
					request.setAttribute("selectedOutletId", current.getOutletId());
					request.setAttribute("currentPrice", current.getOutletItemPrice());
				}
			}

			loadOutlets(request);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error loading item data: " + e.getMessage());
			loadOutlets(request);
		}

		request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
	}

	/**
	 * Prepares and displays report for admin.
	 * Reads the optional "outletId" parameter from the request to fetch data for a 
	 * specific outlet. If missing or invalid, it defaults to 0.
	 * It fetches the report data using ReportDAO and the list 
	 * of all available outlets using OutletDAO, binds them as request attributes, and 
	 * forwards the request to the admin report JSP view.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void viewReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String outletParam = request.getParameter("outletId");
		int outletId = 0;

		if (outletParam != null && !outletParam.trim().isEmpty()) {
			try {
				outletId = Integer.parseInt(outletParam);
			} catch (NumberFormatException e) {
				outletId = 0;
			}
		}

		ReportDAO reportDAO = new ReportDAO();
		ReportModel report = reportDAO.getReport(outletId);

		request.setAttribute("report", report);
		request.setAttribute("selectedOutletId", outletId);

		OutletDAO outletDAO = new OutletDAO();
		request.setAttribute("outlets", outletDAO.getAllOutlets());

		request.getRequestDispatcher("/WEB-INF/views/admin/admin-report.jsp").forward(request, response);
	}
	
	/**
	 * This function is responsible for loading loading contents in the reset password jsp file
	 * validates it the userId exists or not
	 * if yes, passes user data on to the jsp file
	 * 
	 * @param request containing client request data.
     * @param response for handling the resource forward or client redirect.
     * @throws ServletException if the target JSP file encounters a compilation or runtime error during a forward.
     * @throws IOException if an input or output error is detected during a forward or redirect execution.
	 */
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdParam = request.getParameter("userId");
		
		if(userIdParam == null) {
			response.sendRedirect(request.getContextPath() + "/admin/customers");
		}
		
		try {
	        int userId = Integer.parseInt(userIdParam);
	        UserModel user = adminService.getUserById(userId);

	        if (user == null) {
	            response.sendRedirect(request.getContextPath() + "/admin/customers");
	            return;
	        }

	        request.setAttribute("userData", user);
	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-change-password.jsp")
	               .forward(request, response);

	    } catch (NumberFormatException e) {
	        response.sendRedirect(request.getContextPath() + "/admin/customers");
	    }
	}
	
	
	/**
	 * Handles POST requests by routing them to corresponding admin features.
	 * 
	 * Based on the URL path, the method dispatches the request
	 * to specialized handlers for:
	 * - Customer Management (Approval)
	 * - Update Admin Profile
	 * - Menu Management (CRUD)
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 * @see #handleMenuDelete(HttpServletRequest, HttpServletResponse)
	 * @see #handleMenuEdit(HttpServletRequest, HttpServletResponse)
	 * @see #handleCustomerStatus(HttpServletRequest, HttpServletResponse)
	 * @see #handleAdminProfile(HttpServletRequest, HttpServletResponse)
	 * @see #handleMenuAdd(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();

		switch (path) {
			case "/customers/update":
				handleCustomerStatus(request, response);
				break;
			case "/profile":
				handleAdminProfile(request, response);
				break;
			case "/menu/add":
				handleMenuAdd(request, response);
				break;
			case "/menu/edit":
				handleMenuEdit(request, response);
				break;
			case "/menu/delete":
				handleMenuDelete(request, response);
				break;
			case "/customers/reset":
			    handleResetRequest(request, response);
			    break;
			case "/customers/savePassword":
			    handleSavePassword(request, response);
			    break;	
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * Processes the submission form to update the logged-in admin's profile details.
	 * 
	 * Extracts user profile details along with the user ID from the request parameters. 
	 * It invokes AdminService to update the database. Upon a successful transaction, it fetches the fresh 
	 * user details, refreshes the active HTTP session profile attribute, 
	 * and redirects back to the profile page.
	 * If validation, processing, or numeric parsing fails, it redirects with an appropriate error.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void handleAdminProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				//Gets the session to access the current user
				HttpSession session = request.getSession();
				UserModel currentAdmin = (UserModel) session.getAttribute("user");
				
				if(currentAdmin == null) {
					response.sendRedirect(request.getContextPath() + "/login");
					return;
				}
				
				//Extracting updated details
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String dob = request.getParameter("dob");
				String gender = request.getParameter("gender");
				String email = request.getParameter("email");
				String number = request.getParameter("number");
				
				//Running validation for admin
				String validationError = ValidationUtil.adminUpdateValidation(fname, lname, email, number);
				if (validationError != null) {
		             session.setAttribute("error", validationError);
		             response.sendRedirect(request.getContextPath() + "/admin/profile");
		             return;
		         }
				
				try {
					AdminService adminService = new AdminService();
					boolean success = adminService.updateAdminProfile(currentAdmin.getUserId(), fname, lname, dob, gender, email, number);
					
					//Shows if the update was successful or not
					if(success) {
						//Refreshes the data
						UserModel updatedAdmin = adminService.getUserById(currentAdmin.getUserId());
						
						//Keeping the derived image property safe (for JSP)
			            if (updatedAdmin.getImage() == null || updatedAdmin.getImage().isEmpty()) {
			                updatedAdmin.setImage(currentAdmin.getImage());
			            }
						
						//Replaces the old user profile
						session.setAttribute("user", updatedAdmin);
						request.setAttribute("success", "Profile Updated Successfully!");
					}
					else {
						request.setAttribute("error", "Update failed. Please try again.");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "An error occurred: " + e.getMessage());
				}
				response.sendRedirect(request.getContextPath() + "/admin/profile");
	}

	/**
	 * doPost() runs when the Approve or Reject button is clicked
	 * It reads user_id and action from the form, updates the DB, then redirect back
	 */
	private void handleCustomerStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Reads the two values the form send
		// user_id - which user to update
		// action - "approve" or "reject"

		String userIdParam = request.getParameter("user_id");
		String action = request.getParameter("action");
						

		adminService.updateUserStatus(userIdParam, action);
						
		//Always redirect back after a POST - prevent re submission on refresh
		response.sendRedirect(request.getContextPath() + "/admin/customers");
	}
	
	
	/**
	 * Handles admin approve or reject action on a password reset request
	 * On approve, redirect to the change-password page with the userID, so that the admin can set the new password
	 * On reject, clears the reset request and redirects back to the customer management page.
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void handleResetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userIdParam = request.getParameter("user_id");
		String action = request.getParameter("action");
		
		
		//Null validation
		if (userIdParam == null || action == null){
			response.sendRedirect(request.getContextPath() + "/admin/customers");
			return;
		}
		
		int userId;
		
		try {
			userId = Integer.parseInt(userIdParam);			
		}catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/customers");
			return;
		}
		
		if (action.equals("approve")) {
			response.sendRedirect(request.getContextPath() + "/admin/resetPassword?userId=" + userId);
		}else if (action.equals("reject")) {
			adminService.rejectResetRequest(userId);
			response.sendRedirect(request.getContextPath() + "/admin/customers");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/customers");
		}
		
	}
	
	/**
	 * Handles the admin form submission for setting a new password on behalf of a user
	 * 
	 * Reads userId, newPassword, and confirmPassword from the request.
	 * Validates that both password fields matches
	 * 
	 * Redirect back to the change password page with a success or error parameter to display feedback via the JSP
	 * 	 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void handleSavePassword(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		String userIdParam = request.getParameter("user_id");
		String newPassword = request.getParameter("newPassword");
	    String confirmPassword = request.getParameter("confirmPassword");
	    
	    if (userIdParam == null){
	        response.sendRedirect(request.getContextPath() + "/admin/customers");
	        return;
	    }
	    
	    int userId;
	    try {
	        userId = Integer.parseInt(userIdParam);
	    } catch (NumberFormatException e) {
	        response.sendRedirect(request.getContextPath() + "/admin/customers");
	        return;
	    }

	    if (!newPassword.equals(confirmPassword)) {
	    	UserModel user = adminService.getUserById(userId);
	        request.setAttribute("userData", user);
	    	request.setAttribute("errorMessage", "Passwords do not match. Please try again.");
	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-change-password.jsp").forward(request, response);
	        return;
	    }
	    
	    String validationError = ValidationUtil.validatePassword(newPassword, confirmPassword);
	    if (validationError != null) {
	        UserModel user = adminService.getUserById(userId);
	        request.setAttribute("userData", user);
	        request.setAttribute("errorMessage", validationError);
	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-change-password.jsp").forward(request, response);
	        return;
	    }
	    
	    boolean success = adminService.resetPasswordForUser(userId, newPassword);

	    if (success) {
	        response.sendRedirect(request.getContextPath() + "/admin/customers?status=passwordReset");
	    } else {
	    	UserModel user = adminService.getUserById(userId);
	    	request.setAttribute("userData", user);
	    	request.setAttribute("errorMessage", "Something went wrong. Please try again.");
	    	request.getRequestDispatcher("/WEB-INF/views/admin/admin-change-password.jsp").forward(request, response);
	    }
	}
	
	/**
	 * Handles adding a new menu item.
	 * 
	 * Validates required fields (itemName, price, outlerId), handles image upload 
	 * from a multipart request, creates the Item if it doesn't already exist (by name), 
	 * and links the item to the specified outlet with the given price. 
	 * 
	 * If the item exists but different outlet, the existing item is linked to that outlet but 
	 * duplicate linking is prevented. 
	 * 
	 * Expected request parameters:
	 * - itemName (required), price (required), outlerId (required),
	 * - category, itemType, itemDescription, itemStatus, itemIngredient, itemAllergy,
	 * existingImage (optional), multipart part "itemImage" (optional).
	 * 
	 * @param request containing client request data.
     * @param response for rendering the view response.
     * @throws ServletException if the target JSP file encounters an error during rendering.
     * @throws IOException if an input or output error is detected during internal forwarding.
	 */
	private void handleMenuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String itemName = request.getParameter("itemName");
			String priceStr = request.getParameter("price");
			String outletIdStr = request.getParameter("outletId");

			if (itemName == null || itemName.trim().isEmpty() ||
					priceStr == null || priceStr.trim().isEmpty() ||
					outletIdStr == null || outletIdStr.trim().isEmpty() || "0".equals(outletIdStr)) {
				request.setAttribute("message", "Item Name, Price and Outlet are required");
				request.setAttribute("status", "error");

				loadOutlets(request);
				forward(request, response);
				return;
			}

			// Image
			Part part = request.getPart("itemImage");
			ImageUtil imageUtil = new ImageUtil();
			String imagePath = imageUtil.uploadProfileImage(part, "uploads/items", getServletContext());

			ItemDAO itemDAO = new ItemDAO();
			OutletItemDAO outletItemDAO = new OutletItemDAO();
			OutletDAO outletDAO = new OutletDAO();

			int itemId = -1;

			// Check by name only
			ItemModel existing = itemDAO.getItemByName(itemName.trim());

			if (existing != null) {
				itemId = existing.getItemId();
				request.setAttribute("message", "Item already exists. Linking to new outlet...");
			} else {
				ItemModel newItem = new ItemModel(
						itemName.trim(),
						request.getParameter("category"),
						request.getParameter("itemType"),
						request.getParameter("itemDescription"),
						request.getParameter("itemStatus"),
						request.getParameter("itemIngredient"),
						request.getParameter("itemAllergy"),
						imagePath);

				itemId = itemDAO.addItemAndReturnId(newItem);
				request.setAttribute("message", "New item created successfully!");
			}

			// Link to outlet_item
			if (itemId > 0) {
				int outletId = Integer.parseInt(outletIdStr.trim());
				double price = Double.parseDouble(priceStr.trim());

				OutletModel outlet = outletDAO.getOutletById(outletId);
				String outletName = (outlet != null) ? outlet.getOutletName() : "Outlet " + outletId;

				// Check if item already exists in this outlet
				if (outletItemDAO.isItemExistsInOutlet(outletId, itemId)) {
					request.setAttribute("message",
							"This item already exists in the selected outlet! Please use Edit option to change price.");
					request.setAttribute("status", "error");
				} else {
					boolean linked = outletItemDAO.addOrUpdateOutletItem(outletId, itemId, price);

					if (linked) {
						request.setAttribute("message",
								"Item successfully added to " + outletName + " with price Rs." + price);
						request.setAttribute("status", "success");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error: " + e.getMessage());
			request.setAttribute("status", "error");
		}

		loadOutlets(request);
		forward(request, response);
	}
    
	/**
	 * Handles deletion of menu item in both item and its outlet database.
	 * 
	 * Expects "action=delete" and an "itemId" request parameter.
	 * Deletes related outlet_item records first, then deletes the item record itself.
	 * 
	 * @param request containing client request data.
     * @param response for rendering the view response.
     * @throws ServletException if the underlying doGet router or target JSP throws an exception.
     * @throws IOException if an input or output error is detected during processing.
	 */
	private void handleMenuDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIdStr = request.getParameter("itemId");
		String outletIdStr = request.getParameter("outletId");
		String action = request.getParameter("action");

		if (!"confirm".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/admin/admin-delete-item.jsp")
					.forward(request, response);
			return;
		}
		try {
			int itemId = Integer.parseInt(itemIdStr.trim());
			int outletId = Integer.parseInt(outletIdStr.trim());

			OutletItemDAO outletItemDAO = new OutletItemDAO();

			// Deletes only from the specific outlet
			boolean deleted = outletItemDAO.deleteOutletItem(outletId, itemId);

			if (deleted) {
				request.setAttribute("message", "Item successfully removed from this outlet.");
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("message", "Failed to delete item from this outlet.");
				request.setAttribute("status", "error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error deleting item: " + e.getMessage());
			request.setAttribute("status", "error");
		}

		doGet(request, response);
	}

	/**
	 * Processes edits to an existing menu item.
	 * 
	 * Reads item fields (itemId, itemName, category, itemType, description, status, ingredient, allergy) and image upload.
	 * If no new image is provided, it uses existing image as a parameter. Updates the item record in the database.
	 * If outletId and price are provided , updates the outlet_item price.
	 * 
	 * If outletId is missing the price is set to 0 and it does not update.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void handleMenuEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String itemName = request.getParameter("itemName");
			String category = request.getParameter("category");
			String itemType = request.getParameter("itemType");
			String itemDescription = request.getParameter("itemDescription");
			String itemStatus = request.getParameter("itemStatus");
			String itemIngredient = request.getParameter("itemIngredient");
			String itemAllergy = request.getParameter("itemAllergy");
			String existingImage = request.getParameter("existingImage");

			String imagePath = (existingImage != null && !existingImage.trim().isEmpty())
					? existingImage
					: "";

			// Handle new image upload
			if (request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart")) {
				try {
					Part part = request.getPart("itemImage");
					if (part != null && part.getSize() > 0) {
						ImageUtil imageUtil = new ImageUtil();
						imagePath = imageUtil.uploadProfileImage(part, "uploads/items", getServletContext());
					}
				} catch (Exception ex) {
					System.out.println("No new image or error uploading image");
				}
			}

			// Update Item
			ItemModel item = new ItemModel(itemId, itemName, category, itemType,
					itemDescription, itemStatus, itemIngredient, itemAllergy, imagePath);

			ItemDAO itemDAO = new ItemDAO();
			boolean updated = itemDAO.updateItem(item);

			String outletIdStr = request.getParameter("outletId");
			String priceStr = request.getParameter("price");

			boolean priceUpdated = false;

			// Vaildation for outlet and price
			if (outletIdStr == null || outletIdStr.trim().isEmpty() || "0".equals(outletIdStr)) {
				request.setAttribute("message", "Please select an Outlet");
				request.setAttribute("status", "error");
			} else if (priceStr == null || priceStr.trim().isEmpty()) {
				request.setAttribute("message", "Price is required");
				request.setAttribute("status", "error");
			} else {
				try {
					int outletId = Integer.parseInt(outletIdStr.trim());
					double price = Double.parseDouble(priceStr.trim());

					OutletItemDAO outletItemDAO = new OutletItemDAO();
					priceUpdated = outletItemDAO.addOrUpdateOutletItem(outletId, itemId, price);

				} catch (NumberFormatException e) {
					request.setAttribute("message", "Invalid outlet ID or price format.");
					request.setAttribute("status", "error");
				}
			}

			if (updated) {
				String msg = " updated successfully!";
				if (priceUpdated)
					msg += " Price also updated.";
				request.setAttribute("message", msg);
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("message", "Failed to update item.");
				request.setAttribute("status", "error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error updating item: " + e.getMessage());
			request.setAttribute("status", "error");
		}

		// Always reload data
		loadOutlets(request);
		// For pre-filling form
		loadItemForEdit(request); 

		request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp")
				.forward(request, response);
	}
	
	
	/**
	 * Loads all outlets and stores them as a request attribute for outlet view.
	 * 
	 * @param request
	 */
	private void loadOutlets(HttpServletRequest request) {
		OutletDAO outletDAO = new OutletDAO();
		List<OutletModel> outlets = outletDAO.getAllOutlets();
		request.setAttribute("outlets", outlets);
	}

	/**
	 * Extracts and loads a specific menu item's data to edit it
	 * 
	 * Reads the "itemId" and parse it into an integer, and fetches the 
	 * matching item record using ItemDAO.
	 * 
	 * @param request
	 */
	private void loadItemForEdit(HttpServletRequest request) {
		String itemIdStr = request.getParameter("itemId");
		if (itemIdStr != null && !itemIdStr.trim().isEmpty()) {
			try {
				int itemId = Integer.parseInt(itemIdStr);
				ItemDAO itemDAO = new ItemDAO();
				ItemModel item = itemDAO.getItemById(itemId);
				if (item != null) {
					request.setAttribute("item", item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to dispatch the request to the "Add Menu Item" form page.
	 * 
     * @param request containing client request data.
     * @param response for sending the resource redirect.
     * @throws ServletException when the target JSP file throws an exception that prevents the request forward mechanism. 
     * @throws IOException when an input or output error is detected when the servlet handles the redirect or when forwarding the request.
	 */
	private void forward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(req, resp);
		return;
	}

}
