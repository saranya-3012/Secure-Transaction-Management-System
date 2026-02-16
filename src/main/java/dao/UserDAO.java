package dao;

import model.User;
import util.DBConnection;
import java.util.*;

import java.sql.*;

public class UserDAO {

    public void register(User user) throws Exception {
    	
    	// Registration

        String sql = "INSERT INTO users(name,email,phone, password_hash,role) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getphone());
            ps.setString(4, user.getpassword());
            ps.setString(5, user.getrole());

            ps.executeUpdate();
        }
    }

    
    // User Login 
    public User login(String email) throws Exception {

        String sql = "SELECT * FROM users WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setid(rs.getString("id"));
                user.setpassword(rs.getString("password"));
                user.setrole(rs.getString("role"));
                return user;
            }
        }
        return null;
    }
    
    
    // Bulk Insertion
    
    public void bulkInsert(List<User> users) throws Exception {

        String sql = "INSERT INTO users(name,email,password_hash,role) VALUES(?,?,?,?)";

        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement(sql);

        for (User user : users) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getpassword());
            ps.setString(4, user.getrole());
            ps.addBatch();
        }

        ps.executeBatch();
        conn.commit();
    }

}
