package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question18 {

	// Database connection URL, username, and password
	private static final String URL = "jdbc:mysql://localhost:3306/Question18?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "oneplus11R";

	// Method to perform a transaction
	public static void transferFunds(int fromAccountId, int toAccountId, double amount) {
		String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
		String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

		// Use try-with-resources to ensure resources are closed
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement withdrawStmt = connection.prepareStatement(withdrawQuery);
				PreparedStatement depositStmt = connection.prepareStatement(depositQuery)) {

			connection.setAutoCommit(false); // Start transaction

			withdrawStmt.setDouble(1, amount);
			withdrawStmt.setInt(2, fromAccountId);
			withdrawStmt.executeUpdate();

			depositStmt.setDouble(1, amount);
			depositStmt.setInt(2, toAccountId);
			depositStmt.executeUpdate();

			connection.commit(); // Commit transaction

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				if (connection != null) {
					connection.rollback(); // Rollback on error
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		}
	}

	// Method to fetch user details
	public static void getUserDetails(String username) {
		String query = "SELECT id, name, email FROM users WHERE username = ?";

		// Use try-with-resources to ensure resources are closed
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			// Set the parameter safely
			pstmt.setString(1, username);

			try (ResultSet rs = pstmt.executeQuery()) {
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
		// Example usage
		getUserDetails("admin");
		transferFunds(1, 2, 100.0);
	}
}
