package com.quickbite.dao;

import java.sql.Connection;

import com.quickbite.utils.DBconfig;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quickbite.model.UserModel;

public class UserDAO {
	
	public void insertUser (String fname, String lname, String number, String email, String gender, String
			dob, String password, String image) throws Exception{
		Connection con = DBconfig.getConnection();
		
		//? marks serves as the placeholders which is later filled using prepared statement below
		String sql = "INSERT INTO user(fname, lname, number, email, gender, dob, password, role, status, image)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		//Each setString fills one ? in order
		PreparedStatement pst = con.prepareStatement(sql);
		 pst.setString(1, fname);
	     pst.setString(2, lname);
	     pst.setString(3, number);
	     pst.setString(4, email);
	     pst.setString(5, gender);
	     pst.setString(6, dob);
	     pst.setString(7, password);
	     pst.setString(8, "customer");
	     pst.setString(9, "pending");
	     pst.setString(10, image);
	     //This executes the query and saves data to the quickbite database
	     pst.executeUpdate();
	     
	     pst.close();
	     con.close();
	     
	     System.out.println("User inserted into Quickbite DB successfully!");
	}
	
	public UserModel getUserByNumber(String number) {
		String sql = "SELECT * FROM user WHERE number = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql)) {
			
			pst.setString(1, number);
			
			try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    
                    user.setUserId(rs.getInt("user_id"));
                    user.setFname(rs.getString("fname"));
                    user.setLname(rs.getString("lname"));
                    user.setNumber(rs.getString("number"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));
                    user.setDob(rs.getString("dob"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));
                    user.setImage(rs.getString("image"));
                    user.setOutletId(rs.getInt("outlet_id"));
                    return user;
                }
            }
		} catch (SQLException e) {
			System.err.println("Error fetching user: "+e.getMessage());
			e.printStackTrace();
		}
		return null;			
	}
	
	//Fetching all user with status = "pending"
	public List<UserModel> getPendingUsers(){
		List<UserModel> list = new ArrayList<>();
		
		String sql = "SELECT user_id, fname, lname, number, email, role, status, image FROM user WHERE status = 'pending'";
		
		try (Connection conn = DBconfig.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery()){
			
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("user_id"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
		        user.setNumber(rs.getString("number"));
		        user.setEmail(rs.getString("email"));
		        user.setRole(rs.getString("role"));
		        user.setStatus(rs.getString("status"));
		        user.setImage(rs.getString("image"));
		        list.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();	
		}
		return list;
		
	}
	 
	/**
	 * Fetching all users with status = 'active' and role = 'customer'
	 * @return
	 */
	public List<UserModel> getActiveCustomers() {
	    List<UserModel> list = new ArrayList<>();
	    String sql = "SELECT user_id, fname, lname, number, email, role, status, image FROM user WHERE status = 'active' AND role = 'customer'";

	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {
	            UserModel user = new UserModel();
	            user.setUserId(rs.getInt("user_id"));
	            user.setFname(rs.getString("fname"));
	            user.setLname(rs.getString("lname"));
	            user.setNumber(rs.getString("number"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(rs.getString("role"));
	            user.setStatus(rs.getString("status"));
	            user.setImage(rs.getString("image"));
	            list.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	/**
	 * Retrieves all users who have submitted a password reset request.
	 * 
	 * @return a list of UserModel objects with reset request true, 
	 * @see AdminService getPasswordResetRequest()
	 * @since 2026-05-15
	 */
	public List<UserModel> getUsersWithResetRequest(){
		List <UserModel> list = new ArrayList<>();
		String sql = "SELECT user_id, fname, lname, email, number, image FROM user WHERE reset_pwd = 1 AND role = 'customer'";
		
		try(Connection conn = DBconfig.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery()){
			
			while (rs.next()) {
	            UserModel user = new UserModel();
	            user.setUserId(rs.getInt("user_id"));
	            user.setFname(rs.getString("fname"));
	            user.setLname(rs.getString("lname"));
	            user.setEmail(rs.getString("email"));
	            user.setNumber(rs.getString("number"));
	            user.setImage(rs.getString("image"));
	            list.add(user);
	        }
			
		}catch(SQLException e) {
			System.err.println("Error fetching reset requests: " + e.getMessage());
	        e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Sets the password reset request to FALSE for a specified user
	 * @param userId - the unique ID of the user whose request is being cleared
	 * @return true if the update was successful, false otherwise
	 * @since 2026-05-15
	 */
	public boolean clearResetRequest(int userId) {
		String sql = "UPDATE user SET reset_pwd = FALSE WHERE user_id =?";
		

	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setInt(1, userId);
	        return pst.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.err.println("Error clearing reset request: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	public void updateUserStatus(int userId, String newStatus) {
		String sql = "UPDATE user SET status = ? WHERE user_id = ?";
		
		try(Connection conn = DBconfig.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, newStatus);
			pst.setInt(2, userId);
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateUserDetails(int user_id, String fname, String lname, String dob, String gender, String email, String number) {
		String sql = "Update user set fname=?, lname=?, dob=?, gender=?, email=?, number=? where user_id=?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, dob);
			pst.setString(4, gender);
			pst.setString(5, email);
			pst.setString(6, number);
			pst.setInt(7, user_id);
			
			return pst.executeUpdate()>0;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserModel getUserById(int user_id) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		
		try (Connection conn = DBconfig.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql)) {
			
			pst.setInt(1, user_id);
			
			try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    
                    user.setUserId(rs.getInt("user_id"));
                    user.setFname(rs.getString("fname"));
                    user.setLname(rs.getString("lname"));
                    user.setNumber(rs.getString("number"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));

                    java.sql.Date dbDate = rs.getDate("dob");
                    if (dbDate !=null) {
                        user.setDob(dbDate.toString());
                    }
                    
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));
                    user.setImage(rs.getString("image"));
                    return user;
                }
            }
		} catch (SQLException e) {
			System.err.println("Error fetching user: "+e.getMessage());
			e.printStackTrace();
		}

		return null;		
	}
	
	/**
	 * Retrieves a user record matching both the provided email and phone number
	 * 
	 * Performs a verification for the given credentials - Checking if the email and number exist in the db 
	 * 
	 * @param email The email address provided by the user
	 * @param number The phone number provided by the user
	 * @return user object if a matching user is found  or null otherwise
	 * @see UserModel
	 * @since 2026-05-14
	 */
	public UserModel getUserByEmailAndNumber(String email, String number) {
		String sql = "SELECT * FROM user WHERE email = ? AND number = ?";
		
		try(Connection conn = DBconfig.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1,email);
			pst.setString(2,number);
			
			try(ResultSet rs = pst.executeQuery()){
				if (rs.next()) {
					UserModel user = new UserModel();
	                user.setUserId(rs.getInt("user_id"));
	                user.setFname(rs.getString("fname"));
	                user.setLname(rs.getString("lname"));
	                user.setNumber(rs.getString("number"));
	                user.setEmail(rs.getString("email"));
	                user.setRole(rs.getString("role"));
	                user.setStatus(rs.getString("status"));
	                return user;
				}
			}
		}catch(SQLException e) {
			System.err.println("Error fetching user by email and number: " + e.getMessage());
	        e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets the password reset flag for a specified user to True
	 * 
	 * 
	 * @param userId The unique ID of the user requesting a password reset
	 * @return true if the update was successful, false otherwise
	 * @see ResetPasswordService
	 * @since 2026-05-14
	 */
	public boolean setResetPasswordFlag(int userId) {
		String sql = "UPDATE user SET reset_pwd = TRUE WHERE user_id = ?";

		try (Connection conn = DBconfig.getConnection();
	        PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, userId);
				return pst.executeUpdate() > 0;

		    } catch (SQLException e) {
		        System.err.println("Error setting reset_pwd flag: " + e.getMessage());
		        e.printStackTrace();
		        return false;
		    }	
	}

	
	public boolean updatePassword(int userId, String hashNewPassword) {
	    String sql = "UPDATE user SET password = ? WHERE user_id = ?";
	    try (Connection conn = DBconfig.getConnection();
	            PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, hashNewPassword);
	        pst.setInt(2, userId);
	        return pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/**
	 * Updates user's profile image in the database
	 * 
	 * @param userId
	 * @param imagePath
	 * @return
	 */
	public boolean updateUserImage(int userId, String imagePath) {
        String sql = "UPDATE user SET image = ? WHERE user_id = ?";
        
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, imagePath);
            pst.setInt(2, userId);
            
            return pst.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
