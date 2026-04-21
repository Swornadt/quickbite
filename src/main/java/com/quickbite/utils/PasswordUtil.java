package com.quickbite.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
	private static final int COST = 10; //Industry standard cost value
	
	//Converts plain password into hashed string (During Registration Process)
	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(COST));
	}
	
	//Checks if typed password matches the stored hash (During Login Process)
	public static boolean checkPassword(String plainPassword, String storedHash) {
		return BCrypt.checkpw(plainPassword, storedHash);
	}
	
	
}
