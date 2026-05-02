package com.quickbite.service;

import com.quickbite.dao.AdminDAO;
import com.quickbite.model.AdminModel;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAO();
	
	public AdminModel getAdminById(int user_id) {
		AdminModel admin = adminDAO.getAdminById(user_id);
		
		if (admin !=null) {
			String fname = admin.getFname();
			String lname = admin.getLname();
			
			String fullName = fname + " " + lname;
		}
		
		return admin;
	}
}
