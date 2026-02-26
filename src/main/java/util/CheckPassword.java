package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckPassword {
	
   public static String getPassword(String username) {
    	
        try {
        	 String sql = "SELECT * FROM Customer WHERE Username=?";

             try (Connection con = DBConnection.getConnection();
                  PreparedStatement ps = con.prepareStatement(sql)) {

                 ps.setString(1, username);
                 ResultSet rs = ps.executeQuery();

                 if (rs.next()) {                    
                    return rs.getString("password");
                 }
                 else {
                	 return null;
                 }
             }
             

        } 
        catch (Exception e) {
            throw new RuntimeException("Error hashing password");
        }
    }

}
