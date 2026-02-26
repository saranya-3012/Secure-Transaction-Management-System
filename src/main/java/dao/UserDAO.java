package dao;

import model.User;
import util.DBConnection;
import util.PasswordHash;

import java.util.*;

import java.sql.*;

public class UserDAO {
    
   // User Login (Authentication) 
    
    public User login(String email, String password) throws Exception {

        String sql = "SELECT * FROM User WHERE Email=? AND Password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setid(rs.getString("cust_id"));
                user.setName(rs.getString("Name"));
                user.setrole(rs.getString("role"));
                return user;
            }
        }
        return null;
    }
    
}