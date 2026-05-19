package com.quickbite.service;


import java.util.List;
import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;
import com.quickbite.utils.PasswordUtil;

public class AdminService {
	private UserDAO userDAO = new UserDAO();
	
	/**
	 * Pulls a comprehensive user information profile matching a specific identifier key.
	 * 
	 * It accesses the userDAO to run a query matching the provided user_id, and returning a data model.
	 * 
	 * @param user_id the id matching the targeted account row.
	 * @return a UserModel entity containing the account details if found, or null if no record exists.
	 */
	public UserModel getUserById(int user_id) {
		return userDAO.getUserById(user_id);
	}
	
	/**
	 * Updates the details for admin
	 * 
	 * @param user_id the unique identifier for the admin
	 * @param fname the first name of the admin
	 * @param lname the last name of the admin
	 * @param dob the dob that the admin has entered
	 * @param gender the gender selected by the admin
	 * @param email the email address entered by the admin
	 * @param number the phone number entered by the admin
	 * @return parameters to updateUserDetails in the UserDAO
	 */
	public boolean updateAdminProfile(int user_id, String fname, String lname, String dob, String gender, String email, String number) {
		return userDAO.updateUserDetails(user_id, fname, lname, dob, gender, email, number);
	}

	/**
	 * Retrieves all user who have pending status	
	 * @return a list of UserModel objects with pending status
	 */
	public List<UserModel> getPendingUsers(){
		return userDAO.getPendingUsers();
	}
	
	/**
	 * Retrieves all user who have active status	
	 * @return a list of UserModel objects with active status
	 */
	public List<UserModel> getActiveCustomers(){
		return userDAO.getActiveCustomers();
	}
	
	/**
	 * Retrieves all user who have submitted a password reset requests
	 * @return a list of UserModel objects with pending reset requests
	 * @see UserDAO getUsersWithResetRequests();
	 * @since 2026-05-15
	 */
	public List<UserModel> getPasswordResetRequests(){
		return userDAO.getUsersWithResetRequest();
	}
	
	/**
	 * Resets the password for a specified user after admin approval 
	 * 
	 * Hashes the provided plain-text password, updates database and then clears the user reset request.
	 * Both operation must succeed for the method to return true
	 * @param userId the id of user whose password is being reset
	 * @param plainPassword the new plain-text password entered by the admin
	 * @return true if both the password update and request clear succeeded, false otherwise
	 * @since 2026-05-15
	 */
	public boolean resetPasswordForUser(int userId, String plainPassword) {
		String hashed = PasswordUtil.hashPassword(plainPassword);
		boolean passwordUpdated = userDAO.updatePassword(userId, hashed);
		boolean requestCleared = userDAO.clearResetRequest(userId);
		return passwordUpdated && requestCleared;
	}
	
	/**
	 * Rejects a user's password reset request without changing their password (reset_pwdd is set to false)
	 * 
	 * @param userId - The unique ID of the user whose request is being rejected
	 * @return true - if the request was successfully cleared, false otherwise
	 * @see UserDAO clearResetRequest(int)
	 * @since 2026-05-15
	 */
	public boolean rejectResetRequest(int userId) {
		return userDAO.clearResetRequest(userId);
	}
	
	/**
	 * Updates the status of a user account.
	 * 
	 * It validates the raw input strings and maps the action into standard database flag
	 * 
	 * @param userIdParam the raw text string containing targeted userId
	 * @param action the command string detailing whether to approve or reject the registration.
	 * @return true if the validation is clean and the database update executes successfully; false otherwise.
	 */
	public boolean updateUserStatus(String userIdParam, String action) {
		
		//Validation of input
		if(userIdParam == null || action == null) {
			return false;
		}
		
		//Mapping action word to actual DB status value
		String newStatus;
		if(action.equals("approve")) {
			newStatus = "active";
		}else if (action.equals("reject")) {
			newStatus = "rejected";
		}else {
			return false;
		}
		
		//Parsing user_id safely
		int userId;
		try {
			userId = Integer.parseInt(userIdParam);
		}catch(NumberFormatException e) {
			return false;
		}
		
		//All checks passed, now telling DAO to update
		userDAO.updateUserStatus(userId, newStatus);
		return true;
	}

}
