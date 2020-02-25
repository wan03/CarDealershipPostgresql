package com.revature.CarDealership.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static String url;
	private static String username;
	private static String password;
	private static ConnectionFactory cf;
	
	public static Connection getConnection() {
		
		if (cf==null) {
			cf = new ConnectionFactory();
		}
		
		return cf.createConnection();
		
	}
	
	
	private ConnectionFactory() {
		
		String databaseURL = System.getenv("dealership_URL");
		url = "jdbc:postgresql://" + databaseURL + ":5432/dealership";
		username = System.getenv("dealership_DB_username");
		password = System.getenv("dealership_postgress_pass");
		
	}
	
	private Connection createConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Could not load PostgreSQL Driver");
			e1.printStackTrace();
		}

		
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e1) {
			//TODO logging here
			e1.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			 //TODO do some logging here
			
			e.printStackTrace();
		}
		
		return conn;
	}
	


}
