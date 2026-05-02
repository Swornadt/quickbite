package com.quickbite.service;

import com.quickbite.utils.PasswordUtil;
import com.quickbite.dao.UserDAO;

public class RegisterService {
	
	//Receives plain data from controller, hashes password, send to DAO
	public void registerUser(String fname, String lname, String number, String email, String gender, String dob, String password, String imagePath) throws Exception{
		//Hashing plain text password before passing to DAO
		String hashedPassword = PasswordUtil.hashPassword(password);
		
		//Passing all data to DAO for database insertion 
		UserDAO dao = new UserDAO();
		dao.insertUser(fname, lname, number, email, gender, dob, hashedPassword, imagePath);
	}
}
