package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question3 {
	public static void main(String[] args) {
		// Database URL, username, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question3?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;

		try {
			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Step 2: Establish a connection to the database using DriverManager
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connected to database");

			// Step 3: Create and execute a simple SQL statement
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS test_table (id INT PRIMARY KEY, name VARCHAR(50))";
			statement.executeUpdate(sql);
			System.out.println("Table created or already exists");

			// Optional: Close the statement
			statement.close();

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
