package com.quickbite.service;

import com.quickbite.utils.PasswordUtil;
import com.quickbite.dao.UserDAO;

public class RegisterService {
	
	/**
	 * Registers a new user account in the database.
	 * 
	 * @param fname the customer's first name.
     * @param lname the customer's last name.
     * @param number the contact number.
     * @param email the unique email address.
     * @param gender the gender identification.
     * @param dob the text date string representing the user's date of birth.
     * @param password the raw plain-text password chosen by the user.
     * @param imagePath the server file-system path string pointing to the uploaded profile image.
     * @throws Exception if a database execution error or data connection breakdown interrupts the registration process.
	 */
	//Receives plain data from controller, hashes password, send to DAO
	public void registerUser(String fname, String lname, String number, String email, String gender, String dob, String password, String imagePath) throws Exception{
		//Hashing plain text password before passing to DAO
		String hashedPassword = PasswordUtil.hashPassword(password);
		
		//Passing all data to DAO for database insertion 
		UserDAO dao = new UserDAO();
		dao.insertUser(fname, lname, number, email, gender, dob, hashedPassword, imagePath);
	}
}
