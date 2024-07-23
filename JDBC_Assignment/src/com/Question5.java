package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Question5 {
	public static void main(String[] args) {
		// Database URL, username, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question5?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;

		try {
			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Step 2: Establish a connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connected to database");

		} catch (SQLException se) {
			// Handle SQL errors
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}
