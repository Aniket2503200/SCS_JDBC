package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question12 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question12?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Connection connection = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Establish a connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connected to database");

			// Disable auto-commit mode to manage transactions manually
			connection.setAutoCommit(false);

			// Prepare the SQL statements for batch processing
			String insertSql = "INSERT INTO test_table (id, name) VALUES (?, ?)";
			String updateSql = "UPDATE test_table SET name = ? WHERE id = ?";

			try (PreparedStatement insertPstmt = connection.prepareStatement(insertSql);
					PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {

				// Add batch of update operations
				updatePstmt.setString(1, "Varsha");
				updatePstmt.setInt(2, 1);
				updatePstmt.addBatch();

				updatePstmt.setString(1, "Robert");
				updatePstmt.setInt(2, 2);
				updatePstmt.addBatch();

				// Execute the batches
				int[] insertCounts = insertPstmt.executeBatch();
				int[] updateCounts = updatePstmt.executeBatch();

				System.out.println("Batch executed:");
				System.out.println("Rows inserted: " + insertCounts.length);
				System.out.println("Rows updated: " + updateCounts.length);

				// Commit the transaction
				connection.commit();
				System.out.println("Transaction committed");

			} catch (SQLException e) {
				System.err.println("Batch update failed. Rolling back...");
				if (connection != null) {
					try {
						// Roll back the transaction
						connection.rollback();
						System.out.println("Transaction rolled back");
					} catch (SQLException se) {
						se.printStackTrace();
					}
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
