package com.quickbite.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
	private static final int COST = 10; //Industry standard cost value
	
	/**
	 * Converts plain password into hashed string (During Registration Process)
	 * 
	 * It generates a unique random salt based on the cost before computing the final hash.
	 * So, it transforms a plain-text password into a cryptographic hash using the BCrypt algorithm.
	 * 
	 * @param password
	 * @return string representing the secure salt and hashed password characters.
	 */
	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(COST));
	}
	
	/**
	 * Checks if typed password matches the stored hash (During Login Process)
	 * 
	 * Evaluates whether a plain-text login credential matches a cryptographic hash stored
	 * in the database. It safely extracts the original salt from the stored hash and
	 * applies it to the plainPassword to compare the two hashes.
	 * 
	 * @param plainPassword
	 * @param storedHash
	 * @return true if the credentials match; false otherwise.
	 */
	public static boolean checkPassword(String plainPassword, String storedHash) {
		return BCrypt.checkpw(plainPassword, storedHash);
	}
	
	
}
