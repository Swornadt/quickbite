package com.quickbite.service;


import java.util.List;
import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;

public class AdminService {
	private UserDAO userDAO = new UserDAO();
	
	public UserModel getUserById(int user_id) {
		return userDAO.getUserById(user_id);
	}
	
	public boolean updateAdminProfile(int user_id, String fname, String lname, String dob, String gender, String email, String number) {
		return userDAO.updateUserDetails(user_id, fname, lname, dob, gender, email, number);
	}

	//Returning all pending users
	public List<UserModel> getPendingUsers(){
		return userDAO.getPendingUsers();
	}
	
	//Returning all active users
	public List<UserModel> getActiveCustomers(){
		return userDAO.getActiveCustomers();
	}
	
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
