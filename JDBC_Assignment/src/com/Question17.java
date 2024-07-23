package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question17 {

	// Database connection URL, username, and password
	private static final String URL = "jdbc:mysql://localhost:3306/Question17?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "oneplus11R";

	// Method to fetch user details using a secure query
	public static void getUserDetails(String username) {
		String query = "SELECT id, name, email FROM users WHERE username = ?";

		// Use try-with-resources to ensure resources are closed
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			// Set the parameter safely
			pstmt.setString(1, username);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					System.out.println("No user found with the username: " + username);
					return; // Exit if no results
				}

				// Reset the cursor to the first row
				rs.beforeFirst();

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					System.out.printf("ID: %d, Name: %s, Email: %s%n", id, name, email);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Example user input
		String userInput = "admin";
		getUserDetails(userInput);
	}
}
