package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question7 {
	public static void main(String[] args) {
		// Database URL, username, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question7?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Step 2: Establish a connection to the database using DriverManager
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connected to database");

			// Step 3: Create a statement object
			statement = connection.createStatement();

			// Step 4: Execute a SQL SELECT query
			String sql = "SELECT id, name FROM test_table";
			resultSet = statement.executeQuery(sql);

			// Step 5: Process the ResultSet
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: " + id + ", Name: " + name);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Step 6: Close the ResultSet, Statement, and Connection
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
		System.out.println("Done!");
	}
}
