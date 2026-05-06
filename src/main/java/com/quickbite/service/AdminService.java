package com.quickbite.service;

import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;

public class AdminService {
	private UserDAO userDAO = new UserDAO();
	
	public UserModel getUserById(int user_id) {
		return userDAO.getUserById(user_id);
	}
	
	public boolean updateAdminProfile(int user_id, String fname, String lname, String gender, String email, String number) {
		return userDAO.updateUserDetails(user_id, fname, lname, gender, email, number);
	}
}
