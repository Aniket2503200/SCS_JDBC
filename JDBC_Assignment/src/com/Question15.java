package com;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question15 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Question15?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	// Method to insert BLOB and TEXT data
	public static void insertLargeObjects(String name, byte[] blobData, String textData) {
		String insertSQL = "INSERT INTO large_objects (name, blob_data, text_data) VALUES (?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

			preparedStatement.setString(1, name);
			preparedStatement.setBinaryStream(2, new ByteArrayInputStream(blobData));
			preparedStatement.setCharacterStream(3, new StringReader(textData));
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to retrieve BLOB and TEXT data
	public static void retrieveLargeObjects(int id) {
		String selectSQL = "SELECT name, blob_data, text_data FROM large_objects WHERE id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					InputStream blobData = resultSet.getBinaryStream("blob_data");
					Reader textData = resultSet.getCharacterStream("text_data");

					System.out.println("Name: " + name);
					if (blobData != null) {
						// Example: Read and print the size of the BLOB
						System.out.println("BLOB size: " + blobData.available() + " bytes");
					}
					if (textData != null) {
						// Example: Read and print the TEXT data
						char[] buffer = new char[1024];
						int read;
						StringBuilder textContent = new StringBuilder();
						while ((read = textData.read(buffer)) != -1) {
							textContent.append(buffer, 0, read);
						}
						System.out.println("TEXT data: " + textContent.toString());
					}
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Example data
		String name = "Sample Data";
		byte[] blobData = "This is a binary data".getBytes();
		String textData = "This is a large text data for TEXT";

		// Insert data
		insertLargeObjects(name, blobData, textData);

		// Retrieve data
		retrieveLargeObjects(1); // Assuming the ID of the inserted row is 1
		retrieveLargeObjects(2);
	}
}
