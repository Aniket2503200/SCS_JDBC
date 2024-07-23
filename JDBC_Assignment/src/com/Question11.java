package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Question11 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question11?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Connection connection = null;
		Savepoint savepoint = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Establish a connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connected to database");

			// Disable auto-commit mode to manage transactions manually
			connection.setAutoCommit(false);

			try {
				// Perform some database operations
				String insertSql1 = "INSERT INTO test_table (id, name) VALUES (?, ?)";
				try (PreparedStatement pstmt1 = connection.prepareStatement(insertSql1)) {
					pstmt1.setInt(1, 4);
					pstmt1.setString(2, "David");
					pstmt1.executeUpdate();
				}

				// Set a savepoint
				savepoint = connection.setSavepoint("Savepoint1");

				// Perform another operation that could potentially fail
				String insertSql2 = "INSERT INTO test_table (id, name) VALUES (?, ?)";
				try (PreparedStatement pstmt2 = connection.prepareStatement(insertSql2)) {
					pstmt2.setInt(1, 5);
					pstmt2.setString(2, "Eve");
					pstmt2.executeUpdate();
				}

				// Commit the transaction
				connection.commit();
				System.out.println("Transaction committed");

			} catch (SQLException e) {
				System.err.println("Transaction failed. Rolling back...");
				if (connection != null) {
					try {
						// Roll back to the savepoint
						connection.rollback(savepoint);
						System.out.println("Rolled back to savepoint");
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				// Roll back the entire transaction
				try {
					if (connection != null) {
						connection.rollback();
						System.out.println("Transaction rolled back");
					}
				} catch (SQLException se) {
					se.printStackTrace();
				}
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}
