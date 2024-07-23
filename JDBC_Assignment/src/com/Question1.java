package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question1 {
	public static void main(String[] args) {
		// Database URL with SSL disabled
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question1?useSSL=false";
		// Database credentials
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Step 1: Register JDBC driver (optional for some drivers)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Open a connection
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Step 3: Execute a query
			System.out.println("Creating statement...");
			statement = connection.createStatement();
			String sql = "SELECT id, name, age FROM users";
			resultSet = statement.executeQuery(sql);

			// Step 4: Extract data from result set
			while (resultSet.next()) {
				// Retrieve by column name
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Name: " + name);
				System.out.println(", Age: " + age);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Step 5: Clean-up environment
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
		System.out.println("Goodbye!");
	}
}
