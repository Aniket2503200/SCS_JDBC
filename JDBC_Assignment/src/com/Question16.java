package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question16 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question20";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Question16 example = new Question16();

		// Batch processing example
		example.batchInsertUsers();

		// Pagination example
		example.fetchUsersByPage(1, 10);

		// Fetching only required columns
		example.fetchUserByEmail("test@example.com");

		// Transaction management example
		example.performTransaction();
	}

	public void batchInsertUsers() {
		String query = "INSERT INTO users (name, email) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			connection.setAutoCommit(false);
			for (int i = 1; i <= 100; i++) {
				pstmt.setString(1, "User" + i);
				pstmt.setString(2, "user" + i + "@example.com");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			connection.commit();
			System.out.println("Batch insert completed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fetchUsersByPage(int pageNumber, int pageSize) {
		String query = "SELECT * FROM users LIMIT ? OFFSET ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			int offset = (pageNumber - 1) * pageSize;
			pstmt.setInt(1, pageSize);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fetchUserByEmail(String email) {
		String query = "SELECT id, name FROM users WHERE email = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
			} else {
				System.out.println("No user found with email: " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void performTransaction() {
		String insertQuery = "INSERT INTO transactions (amount, description) VALUES (?, ?)";
		String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			connection.setAutoCommit(false);
			try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
					PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

				// Insert a transaction record
				insertStmt.setDouble(1, 100.00);
				insertStmt.setString(2, "Payment");
				insertStmt.executeUpdate();

				// Update account balance
				updateStmt.setDouble(1, 100.00);
				updateStmt.setInt(2, 1); // Account ID
				updateStmt.executeUpdate();

				connection.commit();
				System.out.println("Transaction completed.");
			} catch (SQLException e) {
				connection.rollback(); // Rollback in case of an error
				e.printStackTrace();
				System.out.println("Transaction rolled back.");
			} finally {
				connection.setAutoCommit(true); // Ensure auto-commit is enabled again
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
