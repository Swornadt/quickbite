package com.quickbite.service;

import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;


/**
 * The controller calls methods here
 * This class validates the request and calls the DAO accordingly
 */

public class UserService {

	//Creates a user DAO instance
	private UserDAO userDAO = new UserDAO();
	
	/**
     *  THis methods is used for fetching user from db by their ID.
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
	 * @return 
	 * @throws Exception
	 */

	public boolean updateUserProfile(int userId, String fname, String lname, String dob, String gender,  String email, String number) throws Exception{
		
		
		//Checking for empty space 
		
		if (fname == null || fname.trim().isEmpty()) {
            throw new Exception("First name cannot be empty.");
        }
        if (lname == null || lname.trim().isEmpty()) {
            throw new Exception("Last name cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("Email cannot be empty.");
        }
        if (number == null || number.trim().isEmpty()) {
            throw new Exception("Phone number cannot be empty.");
        }
        
      //Phone Number length validation
        if ( number.length() != 10) {
        	 throw new Exception("Phone number must be exactly 10 digits.");
        }
        
        //Email validation
        if (!email.contains("@gmail.com")) {
        	 throw new Exception("Please enter a valid email address.");
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
