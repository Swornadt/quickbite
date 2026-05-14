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
import com.quickbite.model.Item;
import com.quickbite.model.OrderModel;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;
import com.quickbite.model.Report;
import com.quickbite.model.UserModel;
import com.quickbite.service.AdminCustomerService;
import com.quickbite.service.AdminService;
import com.quickbite.service.MenuService;
import com.quickbite.utils.ImageUtil;
import com.quickbite.utils.SessionUtil;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50   // 50MB
	)
@WebServlet(asyncSupported = true, urlPatterns = { "/admin","/admin/*" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminCustomerService adminCustomerService;
	
    public AdminController() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
        // Initialize the service once when the servlet starts
        this.adminCustomerService = new AdminCustomerService();
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
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
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
			case "/profile":
				viewAdminProfile(request, response);
				break;
			case "/customers/profile":
				viewCustomerProfile(request, response);
				break;
			case "/menu/add":
				viewMenuAdd(request, response);
				break;
			case "/menu/edit":
				System.out.println("edit");
				viewMenuEdit(request, response);
				break;
			case "/menu/delete":
				viewMenuDelete(request, response);
				break;
			case "/report":
				viewReport(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}
	

	/**
	 * Dispatches to Admin Dashboard page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-main-dashboard.jsp").forward(request,response);
	}
	
	/**
	 * Displays Admin's profile.
	 * 
	 * Gets the session of the user currently in session and redirects to login if not available.
	 * Calls adminService to get user by Id and sets attribute 'userData' accordingly.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void viewAdminProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Gets yesterday's date
		LocalDate today = LocalDate.now();
		request.setAttribute("maxDate", today.toString());
		
		UserModel sessionUser = (UserModel) SessionUtil.getAttribute(request, "user");
		
		if (sessionUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		int currentId = sessionUser.getUserId();

		// 1. Initializing the service
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
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void viewCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gets the id from the URL
				String userIdParam = request.getParameter("userId");
				if (userIdParam !=null) {
					try {
						int userId = Integer.parseInt(userIdParam);		
						AdminService adminService = new AdminService();
						OrderDAO orderDAO = new OrderDAO();
						
						UserModel user = adminService.getUserById(userId);			
						if(user !=null) {
							request.setAttribute("customerData", user);
						}
						
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
        List<Outlet> outlets = menuService.getAllOutlets();
        List<OutletItem> outletItems = menuService.getMenuItems(outletParam);

        // Set attributes
        request.setAttribute("outletItems", outletItems);
        request.setAttribute("outlets", outlets);
        request.setAttribute("selectedOutletId", selectedOutletId);

        request.getRequestDispatcher("/WEB-INF/views/admin/admin-menu-view.jsp").forward(request, response);
	}
	
	/**
	 * doGet() runs when the browser visits /admin/customers
	 * It fetches all pending users from the DB and sends them to the JSP
	 */
	private void viewCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("Hit viewCustomers");
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
	
	private void viewMenuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutletDAO outletDAO = new OutletDAO();
        List<Outlet> outlets = outletDAO.getAllOutlets();
        request.setAttribute("outlets", outlets);
        request.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(request, response);
	}
	
	private void viewMenuDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-delete-item.jsp").forward(request, response);
	}

	private void viewMenuEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIdStr = request.getParameter("itemId");

        if (itemIdStr == null || itemIdStr.trim().isEmpty()) {
            request.setAttribute("message", "Item ID is missing!");
            request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr.trim());

            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.getItemById(itemId);

            if (item != null) {
                request.setAttribute("item", item);
            } else {
                request.setAttribute("message", "Item not found with ID: " + itemId);
            }

            // Load outlets for dropdown
            OutletDAO outletDAO = new OutletDAO();
            request.setAttribute("outlets", outletDAO.getAllOutlets());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error loading item data: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
	}
	
	private void viewReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Report report = reportDAO.getReport(outletId);
		
		request.setAttribute("report",  report);
		request.setAttribute("selectedOutletId", outletId);
		
		OutletDAO outletDAO = new OutletDAO();
		request.setAttribute("outlets", outletDAO.getAllOutlets());
		
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-report.jsp").forward(request, response);
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
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 * @see #handleMenuDelete(HttpServletRequest, HttpServletResponse)
	 * @see #handleMenuEdit(HttpServletRequest, HttpServletResponse)
	 * @see #handleCustomerStatus(HttpServletRequest, HttpServletResponse)
	 * @see #handleAdminProfile(HttpServletRequest, HttpServletResponse)
	 * @see #handleMenuAdd(HttpServletRequest, HttpServletResponse)
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}


	private void handleAdminProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
				//Gets the session to access the current user
				HttpSession session = request.getSession();

				String userIdStr = request.getParameter("user_id");
				
				if(userIdStr == null || userIdStr.isEmpty()) {
					response.sendRedirect(request.getContextPath() + "/admin-profile?update=error1");
					return;
				}
				try {
					int user_id = Integer.parseInt(userIdStr);
					String fname = request.getParameter("fname");
					String lname = request.getParameter("lname");
					String dob = request.getParameter("dob");
					String gender = request.getParameter("gender");
					String email = request.getParameter("email");
					String number = request.getParameter("number");
							
					//Update via service
					AdminService adminService = new AdminService();
					boolean success = adminService.updateAdminProfile(user_id, fname, lname, dob, gender, email, number);
					
					if(success) {
						UserModel updatedAdmin = adminService.getUserById(user_id);
						session.setAttribute("user", updatedAdmin);
						response.sendRedirect(request.getContextPath() + "/admin/profile?update=success");
					}
					else {
						response.sendRedirect(request.getContextPath() + "/admin/profile?update=fail");
					}
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/admin-profile?update=error2");
				}
	}
	
	/**
	 * doPost() runs when the Approve or Reject button is clicked
	 * It reads user_id and action from the form, updates the DB, then redirect back
	 */
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
	
	private void handleMenuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String itemName = request.getParameter("itemName");
            String priceStr = request.getParameter("price");
            String outletIdStr = request.getParameter("outletId");

            if (itemName == null || itemName.trim().isEmpty() ||
                    priceStr == null || outletIdStr == null) {
                request.setAttribute("message", "Item Name, Price and Outlet are required");
                request.setAttribute("status", "error");
                forward(request, response);
                return;
            }

            // Image
            Part part = request.getPart("itemImage");
            ImageUtil imageUtil = new ImageUtil();
            String imagePath = imageUtil.uploadProfileImage(part, "uploads/items", getServletContext());

            ItemDAO itemDAO = new ItemDAO();
            OutletItemDAO outletItemDAO = new OutletItemDAO();

            int itemId = -1;

            // Check by name only
            Item existing = itemDAO.getItemByName(itemName.trim());

            if (existing != null) {
                itemId = existing.getItemId();
                request.setAttribute("message", "Item already exists. Linking to new outlet...");
            } else {
                Item newItem = new Item(
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

                // Check if item already exists in this outlet
                if (outletItemDAO.isItemExistsInOutlet(outletId, itemId)) {
                    request.setAttribute("message",
                            "This item already exists in the selected outlet! Please use Edit option to change price.");
                    request.setAttribute("status", "error");
                    loadOutlets(request);
                    forward(request, response);
                    return;
                }

                boolean linked = outletItemDAO.addOrUpdateOutletItem(outletId, itemId, price);

                if (linked) {
                    request.setAttribute("message",
                            "Item successfully linked to outlet " + outletId + " with price " + price);
                    request.setAttribute("status", "success");
                    loadOutlets(request);
                    forward(request, response);
                    return;
                } else {
                    request.setAttribute("message", "Item saved but failed to link to outlet");
                    request.setAttribute("status", "warning");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("status", "error");
        }
	}
    
	private void handleMenuDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIdStr = request.getParameter("itemId");
        String action = request.getParameter("action");

        if (!"delete".equals(action)) {
            doGet(request, response);
            return;
        }
        try {

            int itemId = Integer.parseInt(itemIdStr.trim());

            ItemDAO itemDAO = new ItemDAO();
            OutletItemDAO outletItemDAO = new OutletItemDAO();

            // Step 1: Delete from outlet_item first
            outletItemDAO.deleteOutletItemsByItemId(itemId);

            // Step 2: Delete the item
            boolean itemDeleted = itemDAO.deleteItem(itemId);

            if (itemDeleted) {
                request.setAttribute("message", "Item deleted successfully!");
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("message", "Failed to delete item.");
                request.setAttribute("status", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error deleting item: " + e.getMessage());
            request.setAttribute("status", "error");
        }

        doGet(request, response);
	}

	private void handleMenuEdit(HttpServletRequest request, HttpServletResponse response) {
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

            // Handle image upload
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

            // Create Item object
            Item item = new Item(itemId, itemName, category, itemType,
                    itemDescription, itemStatus, itemIngredient,
                    itemAllergy, imagePath);

            ItemDAO itemDAO = new ItemDAO();
            boolean updated = itemDAO.updateItem(item);

            String outletIdStr = request.getParameter("outletId");
            
            // Validation: If outletId is missing, redirect to a safe default or 0
            if (outletIdStr == null || outletIdStr.trim().isEmpty() || outletIdStr.equals("null")) {
                outletIdStr = "0"; 
            }
            
            String priceStr = request.getParameter("price");

            boolean priceUpdated = false;
            if (outletIdStr != null && !outletIdStr.trim().isEmpty()
                    && priceStr != null && !priceStr.trim().isEmpty()) {

                try {
                    int outletId = Integer.parseInt(outletIdStr.trim());
                    double price = Double.parseDouble(priceStr.trim());

                    OutletItemDAO outletItemDAO = new OutletItemDAO();
                    priceUpdated = outletItemDAO.addOrUpdateOutletItem(outletId, itemId, price);

                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Invalid outlet ID or price format.");
                }
            }

            if (updated) {
                request.setAttribute("message",
                        "Item updated successfully!" + (priceUpdated ? " Price also updated." : ""));
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("message", "Failed to update item in database.");
                request.setAttribute("status", "error");
            }

            
            request.getRequestDispatcher("/WEB-INF/views/admin/admin-update-item.jsp").forward(request, response);
            return;
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error updating item: " + e.getMessage());
            request.setAttribute("status", "error");
        }

	}
	
	
	// Helper Methods
	private void loadOutlets(HttpServletRequest request) {
        OutletDAO outletDAO = new OutletDAO();
        java.util.List<com.quickbite.model.Outlet> outlets = outletDAO.getAllOutlets();
        request.setAttribute("outlets", outlets);
    }
	
    private void forward(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/admin-add-item.jsp").forward(req, resp);
        return;
    }


}
