package com.quickbite.utils;

import java.time.LocalDate;

public class ValidationUtil {

	/**
	 * Handles validation of registration by evaluating incoming user
	 * requests against strict business rules before allowing an account to be created.
	 * 
	 * It checks:
	 * 1. null validation
	 * 2. Regex validation
	 * 3. Phone number length validation
	 * 4. Email domain validation
	 * 5. DOB validation
	 * 6. Password Validation @see #validatePassword(String, String)
	 * 7. Terms agreement
	 * @param fname
	 * @param lname
	 * @param number
	 * @param email
	 * @param dob
	 * @param pass
	 * @param confirmPass
	 * @param terms
	 * @return customer error String if any; else returns null
	 */
	public static String validateRegistration (
	        String fname, String lname, String number, String email, 
	        String dob, String pass, String confirmPass, String terms
	) {
		// null validation
		if (fname == null || lname == null || number == null || email == null || 
		        dob == null || pass == null || confirmPass == null) {
		        return "All fields are required.";
		    }
		
		//Validation of first name and last name
		String fnameError = validateName(fname, "First name");
	    if (fnameError != null) return fnameError;

	    String lnameError = validateName(lname, "Last name");
	    if (lnameError != null) return lnameError;
             
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
        String passwordError = validatePassword(pass, confirmPass);
        if (passwordError != null) {
        	return passwordError;
        }
        
        //Terms must be checked
        if (terms == null) {
            return "You must agree to the Terms of Use.";
        }
        
        return null; //executes if all checks are passed
	}
	
	/**
	 * Handles pre-authentication validation check for filtering out
	 * improperly structured or clearly invalid login credentials.
	 * 
	 * Checks null values and phone number length.
	 * 
	 * @param number the phone number of user to validate login
	 * @param pass the password entered by the user for logging in.
	 * @return custom error String if any; otherwise null.
	 */
	public static String validateLogin (String number, String pass) {
		
		// Checking for empty value in Number and password input field
		if (number == null || number.trim().isEmpty() || pass == null|| pass.trim().isEmpty()) {
			return "Phone number and password are required.";
		}
		
		//Phone number length validation
		if (number.trim().length() != 10) {
			return "Phone number must be exactly 10 digits.";
		}
		
		return null;
	}
	
	/**
	 * Handles password complexity business rules and ensures
	 * credential matching during setup and change password.
	 * 
	 * Validates:
	 * 1. Password length
	 * 2. Presence of special characters, numbers, and capital letters.
	 * 
	 * @param pass he raw plain-text password string submitted for validation.
	 * @param confirmPass the matching validation entry string used to verify the password.
	 * @return custom error String if incorrect; otherwise null
	 */
	public static String validatePassword(String pass, String confirmPass) {
	    if (pass == null || pass.length() <= 6 ||
	            !pass.matches(".*[A-Z].*") ||
	            !pass.matches(".*[0-9].*") ||
	            !pass.matches(".*[!@#$%^&*].*")) {
	        return "Password must be more than 6 characters and include an uppercase letter, a number, and a special character (!@#$%^&*).";
	    }
	    if (!pass.equals(confirmPass)) {
	        return "Passwords do not match.";
	    }
	    return null;
	}
	
	/**
	 * Used for validation of First name and Lastname
	 * Ensuring that value is not null and not blank, contains only letters and spaces.
	 * @param name - The name value to validate
	 * @param fieldLabel - The label to show in error message
	 * @return error string if invalid, otherwise null
	 */
	public static String validateName(String name, String fieldLabel) {
		if (name  == null || name.trim().isEmpty()) {
			return fieldLabel + " cannot be empty.";
		}
		
		if (!name.trim().matches("[a-zA-Z ]+")) {
	        return fieldLabel + " must contain letters only";
	    }
		
		return null;
	}
	
	
	/**
	 * Validates profile update form fields submitted by logged in users
	 * @param fname - updated first name
	 * @param lname - updated last name
	 * @param email - updated email address 
	 * @param number - updated phone number
	 * @return error string if wrong, otherwise null
	 */
	public static String validateProfileUpdate(String fname, String lname, String email, String number) {

	    String fnameError = validateName(fname, "First name");
	    if (fnameError != null) return fnameError;

	    String lnameError = validateName(lname, "Last name");
	    if (lnameError != null) return lnameError;

	    if (email == null || email.trim().isEmpty()) {
	        return "Email cannot be empty.";
	    }
	    if (!email.trim().contains("@gmail.com")) {
	        return "Email address must contain '@gmail.com'.";
	    }

	    if (number == null || number.trim().isEmpty()) {
	        return "Phone number cannot be empty.";
	    }
	    if (number.trim().length() != 10) {
	        return "Phone number must be exactly 10 digits.";
	    }

	    return null;
	}
	
	public static String adminUpdateValidation(String fname, String lname, String email, String number) {
		if(fname == null || fname.trim().isEmpty() ||
				lname == null || lname.trim().isEmpty() ||
				email == null || email.trim().isEmpty() ||
				number == null || number.trim().isEmpty()) {
			return "All fields are to be filled.";
		}
		
		String namePattern = "^[a-zA-Z\\s]+$";
		if(!fname.matches(namePattern)) {
			return "First name must contain only letters.";
		}
		
		if(!lname.matches(namePattern)) {
			return "Last name must contain only letters.";
		}
		
		String phonePatter = "^\\d{10}";
		if(!number.matches(phonePatter)) {
			return "Phone number must be valid.";
		}
		
		if(!email.toLowerCase().endsWith("@gmail.com")) {
			return "Email address must be a valid @gmail.com account.";
		}
		
		return null;
	}
}
