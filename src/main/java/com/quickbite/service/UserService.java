package com.quickbite.service;

import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;
import com.quickbite.utils.ValidationUtil;


/**
 * The controller calls methods here
 * This class validates the request and calls the DAO accordingly
 */

public class UserService {

	//Creates a user DAO instance
	private UserDAO userDAO = new UserDAO();
	
	/**
     * This methods is used for fetching user from db by their ID.
     * Used when loading profile data.
     *
     * @param userId : the ID of the logged-in user (from session)
     * @return UserModel object with all user details, or null if not found
     */
	public UserModel getUserbyId(int userId) {
		return userDAO.getUserById(userId);
	}
	
	/**
	 * For updating a user profile details
	 * This method does the following things
	 *  - Validation of input before passing them to the DAO
	 *  - If validation fails, it throws an exception so the controller can catch it and show an error message
	 *  - If everything is ok, then its calls the DAO
	 * 
	 * @param userId -logged in user is from session
	 * @param fname - new first name from the form
	 * @param lname - new last name form the form 
	 * @param email - new email
	 * @param number - new number
	 * @return true if the database update was successful, otherwise false
	 * @throws Exception - if any validation rule is violated
	 */
	public boolean updateUserProfile(int userId, String fname, String lname, String dob, String gender,  String email, String number) throws Exception{
		
		String validationError = ValidationUtil.validateProfileUpdate(fname, lname, email, number);
	    if (validationError != null) {
	        throw new Exception(validationError);
	    }
     
        return userDAO.updateUserDetails(
                userId,
                fname.trim(),
                lname.trim(),
                dob,
                gender,  
                email.trim(),
                number.trim()
            );
	}
}
