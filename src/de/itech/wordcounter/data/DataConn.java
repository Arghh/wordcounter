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
			//connection = DriverManager.getConnection(null);
			System.out.println("Connected database successfully...");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT INHALT FROM DATA");
			while (resultSet.next()) {
//				System.out.println(resultSet.getFetchSize());
				System.out.println("Loading text...");
				// System.out.println("Inhalt:" + resultSet.getString("INHALT"));
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
			} // nothing we can do
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
	}

	public void dataWrite(TextArea result) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO DATA (INHALT) VALUES ('" + result.getText() + "')";

		try {
			Class.forName("org.h2.Driver");
			System.out.println("Connecting to database...");
			connection = readCredentals();
			System.out.println("Connected database successfully...");
			ps = connection.prepareStatement(sql);
			System.out.println("Inserted records into table...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}

	}
	
	private Connection readCredentals() {
		Properties properties = new Properties();
		Connection connection = null;
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("de/itech/wordcounter/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}
}
