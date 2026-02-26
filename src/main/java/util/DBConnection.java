package util;

import java.sql.*;

public class DBConnection {
	
	// Database Connection
	private static Connection connection;

    public static Connection getConnection() {
    	
    	if (connection == null) {
           try {
        	          	   
        	   String url = "jdbc:postgresql://localhost:5432/bluescope";
        	   String username = "postgres";
        	   String password = "guna";
              connection = DriverManager.getConnection(url, username, password);
           } 
           catch (Exception e) {
              e.printStackTrace();
              
           }
        }
    	return connection;
    }
}
