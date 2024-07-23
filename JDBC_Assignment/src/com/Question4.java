package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question4 {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/Question4?useSSL=false&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "oneplus11R";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			Statement statement = connection.createStatement();
			System.out.println("Connected...");
			// Execute SQL statements...
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
