package com.quickbite.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {
	
	//Details needed to connect to quick bite database 
	
	private static final String URL = "jdbc:mysql://localhost:3306/QuickBite";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	/**
	 * Starts and returns a live connection to the database.
	 * 
	 * This method registers the MySQL JDBC driver class and attempts to
	 * initialize a connection using the predefined URL, username and password.
	 * 
	 * @return active Connection instance if the database connection is successful; null otherwise.
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Connected to QuickBite DB successfully!");
		}catch(Exception e) {
			System.out.println("DB Connection Failed");
			e.printStackTrace();
		}
		return conn;
	}
}
