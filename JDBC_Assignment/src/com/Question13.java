package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Question13 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/question13?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";
	private static final int INITIAL_POOL_SIZE = 10;
	private static final List<Connection> connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
	private static final List<Connection> usedConnections = new ArrayList<>();

	static {
		try {
			for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
				connectionPool.add(createConnection());
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError("Error creating initial connections for the pool: " + e.getMessage());
		}
	}

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public static Connection getConnection() {
		if (connectionPool.isEmpty()) {
			throw new RuntimeException("All connections are in use!");
		}

		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public static boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	public static void main(String[] args) {
		try (Connection connection = getConnection()) {
			System.out.println("Connection obtained from the pool");

			// Create a table
			String createTableSQL = "CREATE TABLE IF NOT EXISTS sample_table (id INT PRIMARY KEY, name VARCHAR(50))";
			try (PreparedStatement createTableStmt = connection.prepareStatement(createTableSQL)) {
				createTableStmt.executeUpdate();
				System.out.println("Table created or already exists");
			}

			// Insert or update data in the table
			String insertOrUpdateSQL = "INSERT INTO sample_table (id, name) VALUES (?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name)";
			try (PreparedStatement insertOrUpdateStmt = connection.prepareStatement(insertOrUpdateSQL)) {
				insertOrUpdateStmt.setInt(1, 1);
				insertOrUpdateStmt.setString(2, "Alice");
				insertOrUpdateStmt.executeUpdate();
				System.out.println("Data inserted or updated");
			}

			// Update data in the table
			String updateSQL = "UPDATE sample_table SET name = ? WHERE id = ?";
			try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
				updateStmt.setString(1, "Alice Updated");
				updateStmt.setInt(2, 1);
				updateStmt.executeUpdate();
				System.out.println("Data updated");
			}

			// Select data from the table
			String selectSQL = "SELECT * FROM sample_table";
			try (PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
					ResultSet resultSet = selectStmt.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					System.out.println("ID: " + id + ", Name: " + name);
				}
			}

			// Delete data from the table
			String deleteSQL = "DELETE FROM sample_table WHERE id = ?";
			try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
				deleteStmt.setInt(1, 1);
				deleteStmt.executeUpdate();
				System.out.println("Data deleted");
			}

			// Release the connection back to the pool
			releaseConnection(connection);
			System.out.println("Connection released back to the pool");
		} catch (SQLException e) {
			System.err.println("SQL error occurred: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Goodbye!");
	}
}
