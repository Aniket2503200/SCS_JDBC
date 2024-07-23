package com;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question6 {
	public static void main(String[] args) {
		// Database URL, username, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question6?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
			System.out.println("Connected to database");

			// Example using Statement
			executeStatementExample(connection);

			// Example using PreparedStatement
			executePreparedStatementExample(connection);

			// Example using CallableStatement
			executeCallableStatementExample(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Goodbye!");
	}

	public static void executeStatementExample(Connection connection) {
		System.out.println("Executing Statement example...");
		try (Statement statement = connection.createStatement()) {
			String sql = "SELECT id, name FROM test_table";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: " + id + ", Name: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void executePreparedStatementExample(Connection connection) {
		System.out.println("Executing PreparedStatement example...");
		String sql = "SELECT id, name FROM test_table WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, 1); // Set the parameter value
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: " + id + ", Name: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void executeCallableStatementExample(Connection connection) {
		System.out.println("Executing CallableStatement example...");
		String sql = "{CALL get_user(?)}";
		try (CallableStatement callableStatement = connection.prepareCall(sql)) {
			callableStatement.setInt(1, 1); // Set the input parameter
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				System.out.println("Name: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
