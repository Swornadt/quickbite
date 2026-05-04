package com.quickbite.utils;

import java.time.LocalDate;

public class ValidationUtil {

	public static String validateRegistration (
	        String fname, String lname, String number, String email, 
	        String dob, String pass, String confirmPass, String terms
	) {
		//First Name validation
        if (!fname.matches("[a-zA-Z ]+")) {
            return "First name must contain letters only.";
        }
        
        //Second Name validation
        if (!lname.matches("[a-zA-Z ]+")) {
            return "Last name must contain letters only.";
        } 
        
        //Phone Number length validation
        if ( number.length() != 10) {
            return "Phone number must be 10 characters (e.g. 9812345678).";
        }
        
        //Email validation
        if (!email.contains("@gmail.com")) {
            return "Email address must contain '@gmail.com'";
        }
        
        //DOB validation
        try {
            LocalDate dobDate = LocalDate.parse(dob);
            if (!dobDate.isBefore(LocalDate.now())) {
                return "Date of birth must be in the past.";
            }
        } catch (Exception e) {
            return "Invalid date format.";
        }
        
        //Password Validation
        if (pass == null || pass.length() <= 6 ||
                !pass.matches(".*[A-Z].*") ||
                !pass.matches(".*[0-9].*") ||
                !pass.matches(".*[!@#$%^&*].*")) {

                return "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).";
            }
        
        //Confirm password matching new password validation
        if (!pass.equals(confirmPass)) {
            return "Passwords do not match.";
        }
        
        //Terms must be checked
        if (terms == null) {
            return "You must agree to the Terms of Use.";
        }
        
        return null; //executes if all checks are passed
	}
	
	public static String validateLogin (String number, String pass) {
		
		// Checking for empty value in Number and password input field
		if (number == null || number.trim().isEmpty() || pass == null|| pass.trim().isEmpty()) {
			return "Phone number and password are required.";
		}
		
		//Phone number length validation
		if (number.trim().length() != 10) {
			return "Phone number must be exactly 10 digits.";
		}
		
		// Password Validation
		if (pass.length() <= 6 ||
                !pass.matches(".*[A-Z].*") ||
                !pass.matches(".*[0-9].*") ||
                !pass.matches(".*[!@#$%^&*].*")) {

		return "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).";
		}
		
		return null;
	}
}
