package dao;

import model.Customer;
import util.DBConnection;
import java.util.*;

import java.sql.*;

public class TransactionDAO {

    public void register(Customer user) throws Exception {
    	
    // Registration

        String sql = "UPDATE Customer SET Type = ? WHERE C";

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
    
    
}