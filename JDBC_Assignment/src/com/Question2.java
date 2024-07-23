package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question2 {
	public static void main(String[] args) {
		// Database URL, username, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question2?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// 1. DriverManager: Register JDBC driver and open a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connected to database");

			// Set auto-commit to false for transaction management
			connection.setAutoCommit(false);

			// 2. Statement: Create a statement object
			statement = connection.createStatement();

			// 3. Execute an insert query
			String insertSQL = "INSERT INTO users (name, age) VALUES ('David', 40)";
			int rowsInserted = statement.executeUpdate(insertSQL);
			System.out.println("Rows inserted: " + rowsInserted);

			// 4. Execute an update query
			String updateSQL = "UPDATE users SET age = 45 WHERE name = 'David'";
			int rowsUpdated = statement.executeUpdate(updateSQL);
			System.out.println("Rows updated: " + rowsUpdated);

			// Commit the transaction
			connection.commit();

			// 5. Execute a select query to verify changes
			String selectSQL = "SELECT id, name, age FROM users";
			resultSet = statement.executeQuery(selectSQL);

			// 6. Process the ResultSet
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			}
		} catch (SQLException se) {
			// Handle SQL errors
			se.printStackTrace();
			try {
				if (connection != null) {
					// Rollback the transaction on error
					connection.rollback();
				}
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
