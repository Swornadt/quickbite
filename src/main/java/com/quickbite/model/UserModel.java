package com.quickbite.model;

/**
 * Represents a user in the Quickbite system.
 */
public class UserModel {
	
	//Registration form input fields;
	private int userId;
	private String fname;
	private String lname;
	private String number;
	private String email;
	private String gender;
	private String dob;
	private String password;
	private String role = "customer";
    private String status = "pending";
    private String image;
	private int outletId;
	
	//Getter and Setter 
	
    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID
     */
    public int getUserId() {
    	return userId;
    }
    
    /**
     * Returns the first name of the user.
     *
     * @return the first name
     */
	public String getFname() {
        return fname;
    }

	 /**
     * Returns the last name of the user.
     *
     * @return the last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return the phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the gender of the user.
     *
     * @return the gender
     */  
    public String getGender() {
        return gender;
    }

    /**
     * Returns the date of birth of the user.
     *
     * @return the date of birth as a String
     */
    public String getDob() {
        return dob;
    }

    /**
     * Returns the hashed password of the user.
     *
     * @return the hashed password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Returns the outlet ID associated with the user.
     *
     * @return the outlet ID
     */
    public int getOutletId() {
        return outletId;
    }
    
    /**
     * Sets the unique identifier of the user.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
    	this.userId = userId;
    }

    /**
     * Sets the first name of the user.
     *
     * @param fname the first name to set
     */

    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lname the last name to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param number the phone number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param date the date of birth to set
     */
    public void setDob(String date) {
        this.dob = date;
    }

    /**
     * Sets the password of the user.
     * 
     * The password should already be hashed with BCrypt before calling this method.
     *     
     * @param password the hashed password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role assigned to the user.
     * 
     * 
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Returns the approval status of the user account.
     * 
     * Default value is {@code "pending"}.
     *
     * @return the status (e.g., "pending" or "approved")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the approval status of the user account.
     *
     * @param status the status to set (e.g., "pending" or "approved")
     */
    public void setStatus(String status) {
        this.status = status;
    }
 
    /**
     * Returns the full name of the user by combining first and last name.
     *
     * @return the full name in "firstname lastname" format
     */    
    public String getFullName() {
    	return fname + " " + lname;
	}
    
    /**
     * Returns the profile image filename of the user.
     *
     * @return the image filename
     */
    public String getImage() {
    	return image;
    }
    
    /**
     * Sets the profile image filename of the user.
     *
     * @param image the image filename to set
     */    
    public void setImage(String image) {
    	this.image = image;
    }

    /**
     * Sets the outlet ID associated with the user.
     *
     * @param outletId the outlet ID to set
     */    
    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }
    
}
