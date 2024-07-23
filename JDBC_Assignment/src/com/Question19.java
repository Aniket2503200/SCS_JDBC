package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question19 {

	// Database connection URL, username, and password
	private static final String URL = "jdbc:mysql://localhost:3306/Question19?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "oneplus11R";

	// Method to insert a record into the 'users' table
	public static void insertRecord(String name, String email) {
		String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";

		// Use try-with-resources to ensure resources are closed
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {

			// Set the parameters for the prepared statement
			pstmt.setString(1, name);
			pstmt.setString(2, email);

			// Execute the insert operation
			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Record inserted successfully. Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Example usage
		insertRecord("Aniket", "AniketUgale@example.com");
	}
}
