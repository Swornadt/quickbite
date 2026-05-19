package com.quickbite.service;

import com.quickbite.dao.UserDAO;
import com.quickbite.model.UserModel;
import com.quickbite.utils.PasswordUtil;

public class LoginService {
    private UserDAO userDAO = new UserDAO();

    /**
     * It is used to authenticate users according to their phone number and password stored in the database
     * 
     * @param number the phone number entered by the user which will be matched with the number in the database for the user.
     * @param plainPassword the password entered by the user.
     */
    public UserModel authenticate(String number, String plainPassword) {
        // 1. Fetch user from DB by phone number
        UserModel user = userDAO.getUserByNumber(number);

        // 2. If user exists check the password hash
        if (user != null) {
            boolean isPasswordMatch = PasswordUtil.checkPassword(plainPassword, user.getPassword());
            if (isPasswordMatch) {
                return user;
            }
        }
        
        return null;
    }
}