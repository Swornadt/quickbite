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
import java.sql.SQLException;
import java.util.List;

import com.quickbite.dao.FavoriteDAO;
import com.quickbite.dao.OrderDAO;
import com.quickbite.dao.UserDAO;
import com.quickbite.model.ItemModel;
import com.quickbite.model.OrderModel;
import com.quickbite.model.UserModel;
import com.quickbite.service.FeedbackService;
import com.quickbite.service.UserService;
import com.quickbite.utils.ImageUtil;
import com.quickbite.utils.PasswordUtil;
import com.quickbite.utils.SessionUtil;
import com.quickbite.utils.ValidationUtil;
import com.quickbite.model.OutletItemModel;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
	    maxFileSize = 1024 * 1024 * 10,       // 10MB
	    maxRequestSize = 1024 * 1024 * 50     // 50MB
	)

@WebServlet(asyncSupported = true, urlPatterns = { "/profile", "/profile/*" })
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAO();
	
	private UserService userService = new UserService();
	
    public CustomerController() {
        super();
    }
    

    /**
     * Handles GET requests for User Profile's actions
     * 
     * Based on the endpoint of the current URL, the corresponding method is called.
     * 1. Profile
     * 2. Order History
     * 3. Change Password
     * 4. View Favorites
     * 
     * @param request
     * @param response
     * @throws ServletException, IOException
     * @see #viewChangePassword(HttpServletRequest, HttpServletResponse)
     * @see #viewCustomerOrderHistory(HttpServletRequest, HttpServletResponse)
     * @see #viewFavorites(HttpServletRequest, HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		
		if (endpoint == null || endpoint.equals("/")) {
			viewUserProfile(request,response);
            return;
        }
		
		switch(endpoint) {
			case "/order-history":
				viewCustomerOrderHistory(request, response);
				break;
			case "/change-password":
				viewChangePassword(request, response);
				break;
			case "/feedback":
				request.getRequestDispatcher("/WEB-INF/views/public/feedback.jsp").forward(request, response);
				break;
			case "/favorites":
				viewFavorites(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}
	
	/**
     * doPost handles all POST requests
     * We check the path to know which form was submitted.
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getPathInfo();
		

		if (endpoint == null || endpoint.equals("/")) {
			viewUserProfile(request, response);
            return;
        }
		
		switch(endpoint) {
			case "/favorites/toggle":
				toggleFavorites(request, response);
				break;
			case "/feedback":
			    submitOrderFeedback(request, response);
			    break;	
			case "/update":
				updateUserProfile(request,response);
				break;
			case "/change-password":
				handleChangePassword(request, response);
				break;
			case "/upload-image":
				uploadProfileImage(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
	
		// check if new passwords match
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("error", "New passwords must match");
			request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
			return;
		}
		
		// validate new password format and confirmation match
		String validationError = ValidationUtil.validatePassword(newPassword, confirmPassword);
		if (validationError != null) {
		    request.setAttribute("error", validationError);
		    request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
		    return;
		}
		
		// get current user from session for password validation
		UserModel currentUser = (UserModel) SessionUtil.getAttribute(request, "user");
		if (currentUser == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// fetching user from DB to get hash
		UserDAO userDAO = new UserDAO();
		UserModel dbUser = userDAO.getUserById(currentUser.getUserId());
		// verify old password
		if (!PasswordUtil.checkPassword(oldPassword, dbUser.getPassword())) {
			request.setAttribute("error", "Old password is incorrect.");
			request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
			return;
		}
		
		// hash new pass and update
		String hashNewPassword = PasswordUtil.hashPassword(newPassword);
		boolean success = userDAO.updatePassword(currentUser.getUserId(), hashNewPassword);
		
		if (success) {
			request.setAttribute("success", "Password changed successfully.");
		} else {
			request.setAttribute("error", "Failed to update password. Please try again.");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
	}


	/**
     * Loads the Profile Page. 
     * Gets the Logged in user from the session and puts them into the request
     * so the jsp can access them with <=%user.getFname()>
     */
    private void viewUserProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	HttpSession session = request.getSession();
    	UserModel user = (UserModel) session.getAttribute("user");
    	
    	//If no user is in session, they're not logged in
    	if (user == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
    	
    	session.setAttribute("user", user);
    	
    	request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request,response);
    }
	
    /**
     * Handles the profile update form submission (POST /profile/update).
     */
    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	 HttpSession session = request.getSession();
         UserModel currentUser = (UserModel) session.getAttribute("user");

         ///If no user is in session, they're not logged in
         if (currentUser == null) {
             response.sendRedirect(request.getContextPath() + "/login");
             return;
         }
      
         // reading form values
         String fname = request.getParameter("fname");
         String lname = request.getParameter("lname");
         String email = request.getParameter("email");
         String number = request.getParameter("number");
         
         try {
        	 //Calling the service layer
        	 boolean success = userService.updateUserProfile(currentUser.getUserId(), fname, lname,currentUser.getDob(), currentUser.getGender(), email,number);
        	 
        	 //After the new data is updated in DB, we fetch the latest record and put it back in session
        	 //This ensures that other components such as navbar, profile page etc. shows new user data 
        	 if (success) {
        		 UserModel updatedUser = userService.getUserbyId(currentUser.getUserId());
        		 session.setAttribute("user",updatedUser);
        		 
        		 request.setAttribute("success", "Profile updated Sucessfully!");
        	 }else {
        		 request.setAttribute("error", "Update failed. Please try again.");
        	 }
         }catch(Exception e) {
        	 request.setAttribute("error", e.getMessage()); 
         }
         
         request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request, response);
         
         
    }
    
	/**
	 * Displays the past and ongoing orders of the current user.
	 * 
	 * Gets the user's details from the logged in session, and fetches
	 * the past and current orders through the DAO, passing true and false
	 * for current and past respectively.
	 * The data is then passed to jsp through attributes.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void viewCustomerOrderHistory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// fetch data by dao
		List<OrderModel> currentOrders = orderDAO.getOrdersByStatus(user.getUserId(), true);
        List<OrderModel> pastOrders = orderDAO.getOrdersByStatus(user.getUserId(), false);
        
        // setting those attributes to jsp
        request.setAttribute("currentOrders", currentOrders);
        request.setAttribute("pastOrders", pastOrders);
        
		request.getRequestDispatcher("/WEB-INF/views/customer/profile/order-history.jsp").forward(request, response);		
	}

	private void submitOrderFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    
	    if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("error", "You must be logged in to submit feedback.");
            request.getRequestDispatcher("/WEB-INF/views/public/feedback.jsp").forward(request, response);
            return;
        }
	    
	    UserModel user = (UserModel) session.getAttribute("user");
        String ratingStr = request.getParameter("rating");
        String message = request.getParameter("message");
        String orderIdStr = request.getParameter("orderId");
        
        if (ratingStr == null || ratingStr.trim().isEmpty() ||
            message == null || message.trim().isEmpty()) {
            request.setAttribute("error", "Rating and message are required.");
            request.getRequestDispatcher("/WEB-INF/views/public/feedback.jsp").forward(request, response);
            return;
        }

        try {
            int rating = Integer.parseInt(ratingStr);
            int orderId = Integer.parseInt(orderIdStr);
            
            if (rating < 1 || rating > 5) {
                request.setAttribute("error", "Rating must be between 1 and 5.");
                request.getRequestDispatcher("/WEB-INF/views/public/feedback.jsp").forward(request, response);
                return;
            }
            
            FeedbackService service = new FeedbackService();
            service.submitFeedback(orderId, rating, message.trim());

            request.setAttribute("success", "Thank you! Your feedback has been submitted successfully.");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid rating value.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong. Please try again later.");
        }

        request.getRequestDispatcher("/WEB-INF/views/customer/profile/order-history.jsp").forward(request, response);
    }
	
	private void viewChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/profile/change-password.jsp").forward(request, response);
	}

	private void viewFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    UserModel user = (session != null) ? (UserModel) session.getAttribute("user") : null;

	    if (user == null) {
	        // Redirect to login if the session is gone
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }

	    FavoriteDAO favDAO = new FavoriteDAO();
	    try {
	        List<OutletItemModel> favoriteList = favDAO.getFavoritesByUser(user.getUserId());
	        request.setAttribute("favoriteList", favoriteList);
	        System.out.println(favoriteList);
	        request.getRequestDispatcher("/WEB-INF/views/customer/profile/favorites-page.jsp").forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace(); // This will show the real error in your Eclipse/IntelliJ console
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}
	

	private void toggleFavorites(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
	    UserModel user = (UserModel) session.getAttribute("user");

	    if (user == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return;
	    }

	    int itemId = Integer.parseInt(request.getParameter("itemId"));
	    int outletId = Integer.parseInt(request.getParameter("outletId"));
	    int userId = user.getUserId();

	    FavoriteDAO favDAO = new FavoriteDAO();
	    try {
	    	
	        if (favDAO.isFavorite(userId, itemId, outletId)) {
	            favDAO.removeFavorite(userId, itemId, outletId);
	        } else {
	            favDAO.addFavorite(userId, itemId, outletId);
	        }
	        
	        // getting the details of the previous page through header
	        String referer = request.getHeader("Referer");
	        
	        if (referer != null && !referer.isEmpty()) {
	        	response.sendRedirect(referer);
	        } else {
	        	response.sendRedirect(request.getContextPath() + "/profile/favorites");
	        }
	    } catch (SQLException | NumberFormatException e) {
	    	e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }

	}
	
	/**
     * Handles profile image upload
     * POST /profile/upload-image
     */
    private void uploadProfileImage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            Part part = request.getPart("profileImage");
            
            if (part == null || part.getSize() == 0) {
                request.setAttribute("error", "Please select an image to upload.");
                request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request, response);
                return;
            }

            ImageUtil imageUtil = new ImageUtil();
            String imagePath = imageUtil.uploadProfileImage(part, "uploads", getServletContext());

            // Update image path in database
            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.updateUserImage(user.getUserId(), imagePath);

            if (success) {
                // Update session with new image path
                user.setImage(imagePath);
                session.setAttribute("user", user);
                
                request.setAttribute("success", "Profile picture updated successfully!");
            } else {
                request.setAttribute("error", "Failed to update profile picture.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error uploading image. Please try again.");
        }

        // Forward back to profile page
        request.getRequestDispatcher("/WEB-INF/views/customer/profile/user-profile.jsp").forward(request, response);
    }

}
