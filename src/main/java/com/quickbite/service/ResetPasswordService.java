package com.quickbite.service;

import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;

public class ResetPasswordService {
	private final UserDAO userDAO = new UserDAO();
	
	/**
	 * Validates whether a user account exists with the given email and phone number
	 * Both fields must match the same user record for passing validation
	 *  
	 * @param email - The email address submitted by the user on reset form
	 * @param number - The phone number submitted by the user
	 * @return userModel if a matching account is found, null otherwise
	 * @see UserDAO.getUserByEmailAndNumber(String, String)
	 * @since 2026-05-14
	 */
	public UserModel validateUser(String email, String number) {
		return userDAO.getUserByEmailAndNumber(email, number);
	}
	
	/**
	 * Uses dao function to set the reset pw to true, upon user request
	 * 
	 * @param userId - The unique ID of the user requesting a password reset.
	 * @return true - if the reset was successfully applied
	 * @see UserDAO.setResetPasswordFlag(int)
	 * @since 2026-05-14
	 */
	public boolean requestPasswordReset(int userId) {
		return userDAO.setResetPasswordFlag(userId);
	}
}
