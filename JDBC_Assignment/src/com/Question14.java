package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question14 {

	// ThreadLocal to store a connection per thread
	private static final ThreadLocal<Connection> threadLocalConnection = ThreadLocal.withInitial(() -> {
		try {
			// Initialize the connection with SSL disabled (or enabled if needed)
			String jdbcUrl = "jdbc:mysql://localhost:3306/Question14?useSSL=false"; // Disable SSL
			// Alternatively, use this line to enable SSL
			// String jdbcUrl =
			// "jdbc:mysql://localhost:3306/yourdb?useSSL=true&verifyServerCertificate=true&requireSSL=true";

			return DriverManager.getConnection(jdbcUrl, "root", "oneplus11R");
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create database connection", e);
		}
	});

	// Method to get the connection
	private static Connection getConnection() {
		return threadLocalConnection.get();
	}

	// Method to close the connection
	private static void closeConnection() {
		Connection connection = threadLocalConnection.get();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				threadLocalConnection.remove();
			}
		}
	}

	// DatabaseTask class to perform database operations
	private static class DatabaseTask implements Runnable {
		@Override
		public void run() {
			try (Connection connection = getConnection()) {
				String query = "SELECT * FROM your_table";
				try (PreparedStatement statement = connection.prepareStatement(query);
						ResultSet resultSet = statement.executeQuery()) {

					while (resultSet.next()) {
						// Process the result set
						System.out.println(resultSet.getString("your_column"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
	}

	// Main class to start threads
	public static void main(String[] args) {
		// Create and start multiple threads
		for (int i = 0; i < 5; i++) {
			new Thread(new DatabaseTask()).start();
		}
	}
}
