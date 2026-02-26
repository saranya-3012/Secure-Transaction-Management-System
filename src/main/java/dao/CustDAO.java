package dao;

import model.Customer;
import util.DBConnection;
import util.PasswordHash;

import java.util.*;

import java.sql.*;

public class CustDAO {

    public void register(Customer user) throws Exception {
    	
    	// Registration

        String sql = "INSERT INTO Customer(Name, Email, Phone, Password) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getphone());
            ps.setString(4, user.getpassword());

            ps.executeUpdate();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    

    
   // Customer Login (Authentication) 
    
    public Customer login(String email, String password) throws Exception {

        String sql = "SELECT * FROM Customer WHERE Email=? AND Password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer cust = new Customer();
                cust.setid(rs.getString("cust_id"));
                cust.setName(rs.getString("Name"));
                cust.setrole(rs.getString("role"));
                cust.setEmail(rs.getString("email"));
                cust.setphone(rs.getString("phone"));
                return cust;
            }
        }
        return null;
    }
    
    
//    // Bulk Insertion
//    
//    public void bulkInsert(List<Users> users) throws Exception {
//
//        String sql = "INSERT INTO users(name,email,password_hash,role) VALUES(?,?,?,?)";
//
//        Connection conn = DBConnection.getConnection();
//        conn.setAutoCommit(false);
//
//        PreparedStatement ps = conn.prepareStatement(sql);
//
//        for (Users user : users) {
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getEmail());
//            ps.setString(3, user.getpassword());
//            ps.setString(4, user.getrole());
//            ps.addBatch();
//        }
//
//        ps.executeBatch();
//        conn.commit();
//    }
//    
//    
//    public void updateStatus(int userId, String status) throws Exception {
//
//        String sql = "UPDATE users SET status=? WHERE id=?";
//        Connection con = DBConnection.getConnection();
//        PreparedStatement ps = con.prepareStatement(sql);
//
//        ps.setString(1, status);
//        ps.setInt(2, userId);
//
//        ps.executeUpdate();
//        con.close();
//    }
//    
//    
//    
//    public Users getUserById(int id) throws Exception {
//
//        String sql = "SELECT * FROM users WHERE id=?";
//        Connection con = DBUtils.getConnection();
//        PreparedStatement ps = con.prepareStatement(sql);
//
//        ps.setInt(1, id);
//
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            Users u = new Users();
//            u.setId(rs.getInt("id"));
//            u.setName(rs.getString("name"));
//            u.setEmail(rs.getString("email"));
//            u.setBalance(rs.getDouble("balance"));
//            u.setRole(rs.getString("role"));
//            u.setStatus(rs.getString("status"));
//            con.close();
//            return u;
//        }
//
//        con.close();
//        return null;
//    }



}