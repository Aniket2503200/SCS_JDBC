package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question9 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question9?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		// SQL query to select all records from the test_table
		String sql = "SELECT id, name FROM test_table";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			// Print the results
			System.out.println("ResultSet example:");

			// Navigate and retrieve data from ResultSet
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: " + id + ", Name: " + name);
			}

			// Navigate to the first row (if applicable)
			if (resultSet.first()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("First row - ID: " + id + ", Name: " + name);
			}

			// Navigate to the last row (if applicable)
			if (resultSet.last()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Last row - ID: " + id + ", Name: " + name);
			}

			// Navigate to the second row from the start (if applicable)
			if (resultSet.absolute(2)) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Second row - ID: " + id + ", Name: " + name);
			}

			// Move cursor relative to the current position
			if (resultSet.relative(1)) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Relative row - ID: " + id + ", Name: " + name);
			}

		} catch (SQLException se) {
			System.err.println("SQL error occurred: " + se.getMessage());
			se.printStackTrace();
		}
	}
}
