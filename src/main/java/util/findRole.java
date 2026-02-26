package util;

import java.sql.*;

public class findRole {

    public static String authenticate(String username, String password) throws Exception {

        Connection conn = DBConnection.getConnection();
        
      // Find which role the user have
        String sql = "SELECT role FROM Customers WHERE username=? AND password=? "
        		  + " UNION SELECT role FROM Admin WHERE username=? AND password=?";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
        	
        	ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
            else {
            	return "Invalid User!";
            }
            
        }
        
    }
}