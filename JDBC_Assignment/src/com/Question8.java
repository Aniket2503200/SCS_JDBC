package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question8 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question8?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Create a new record
			createRecord(3, "Omkar");

			// Read all records
			readRecords();

			// Update a record
			updateRecord(2, "Varsha");

			// Read all records again to see the update
			readRecords();

			// Delete a record
			deleteRecord(3);

			// Read all records again to see the deletion
			readRecords();

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void createRecord(int id, String name) {
		String sql = "INSERT INTO test_table (id, name) VALUES (?, ?)";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			int rowsInserted = preparedStatement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("A new record was inserted successfully.");
			}
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void readRecords() {
		String sql = "SELECT id, name FROM test_table";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			System.out.println("Reading records:");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: " + id + ", Name: " + name);
			}
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void updateRecord(int id, String newName) {
		String sql = "UPDATE test_table SET name = ? WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);
			int rowsUpdated = preparedStatement.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Record updated successfully.");
			}
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void deleteRecord(int id) {
		String sql = "DELETE FROM test_table WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			int rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Record deleted successfully.");
			}
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
