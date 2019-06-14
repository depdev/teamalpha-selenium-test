package com.teamalpha.selenium.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import org.testng.annotations.BeforeSuite;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

	private static final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://3.8.115.205:8082/teamalpha_db";   
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ".teamalpha.";
    
    private DatabaseConnector () {
    	
    }

	@BeforeSuite(alwaysRun = true)
    public static Connection getDBConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}

		try {
			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return connection;
		} catch (SQLException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
		
       return connection;
    }
    
//    @AfterSuite(alwaysRun = true)
//	public void tearDown() throws Exception {
//	       // Close DB connection
//	       if (connection != null) {
//	    	   connection.close();
//	       }
//	}
}