package com.amc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	private static Connection connection = null;

	private DbUtil() {
	}

	static {
		String driverClass = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/amc";
		String dbuser = "postgres";
		String dbpass = "test";

		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException ......" + e);
		} catch (SQLException e) {
			System.out.println("SQLException in connecting time...." + e);
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
