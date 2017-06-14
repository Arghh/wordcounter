package de.itech.wordcounter.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javafx.scene.control.TextArea;

public class DataConn {

	public void readData(TextArea result) {
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			System.out.println("Connecting to database...");
			connection = readCredentals();
			System.out.println("Connected database successfully...");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT INHALT FROM DATA");
			while (resultSet.next()) {
				System.out.println("Loading text...");
				result.setText(resultSet.getString("INHALT"));
			}

			resultSet.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
	}

	public void dataWrite(TextArea result) {
		try {
			Connection connection = null;
			PreparedStatement ps = null;
			Class.forName("org.h2.Driver");
			System.out.println("Connecting to database...");
			connection = readCredentals();
			System.out.println("Connected database successfully...");
			String sql = "INSERT INTO DATA (INHALT) VALUES(?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, result.getText());
			System.out.println("Inserting records into table...");
			ps.executeUpdate();
			ps.close();
			System.out.println("Inserted records into table successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	private Connection readCredentals() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("de/itech/wordcounter/config.properties"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}
}
