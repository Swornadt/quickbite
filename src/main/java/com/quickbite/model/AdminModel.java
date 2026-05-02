package com.quickbite.model;

import java.sql.Date;

public class AdminModel {
	private int user_id;
	private String fname;
	private String lname;
	private String number;
	private String email;
	private String gender;
	private Date dob;
	private String password;
	private String role = "Admin";
    private String status = "active";
    
    public int getUserId() {
    	return user_id;
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

    public Date getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getStatus() {
        return status;
    }
    
    
    
    public void setUserId(int user_id) {
    	this.user_id = user_id;
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

    public void setDob(Date date) {
        this.dob = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFullName() {
    	return fname + " " + lname;
    }
}
