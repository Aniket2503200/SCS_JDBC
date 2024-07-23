package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question10 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question10?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			// Create a connection
			try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
				System.out.println("Connected to database");

				// Example with TYPE_FORWARD_ONLY
				System.out.println("ResultSet TYPE_FORWARD_ONLY:");
				try (Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery("SELECT id, name FROM test_table")) {

					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						System.out.println("ID: " + id + ", Name: " + name);
					}
				}

				// Example with TYPE_SCROLL_INSENSITIVE
				System.out.println("ResultSet TYPE_SCROLL_INSENSITIVE:");
				try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery("SELECT id, name FROM test_table")) {

					// Move to first row
					if (rs.first()) {
						System.out.println("First row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}

					// Move to last row
					if (rs.last()) {
						System.out.println("Last row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}

					// Move to a specific row
					if (rs.absolute(2)) {
						System.out.println("Second row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}
				}

				// Example with TYPE_SCROLL_SENSITIVE
				System.out.println("ResultSet TYPE_SCROLL_SENSITIVE:");
				try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery("SELECT id, name FROM test_table")) {

					// Move to first row
					if (rs.first()) {
						System.out.println("First row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}

					// Move to last row
					if (rs.last()) {
						System.out.println("Last row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}

					// Move to a specific row
					if (rs.absolute(2)) {
						System.out.println("Second row - ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
					}
				}

			} catch (SQLException e) {
				System.err.println("SQL error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
