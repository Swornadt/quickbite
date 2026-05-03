package com.quickbite.model;

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
	
	//Getter and Setter 
    public int getUserId() {
    	return userId;
    }
    
	public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFullName() {
    	return fname + " " + lname;
	}
      
    public String getImage() {
    	return image;
    }
    
    public void setImage(String image) {
    	this.image = image;
    }
    
    public String getFullName() {
    	return fname + " " + lname;
    }
}
