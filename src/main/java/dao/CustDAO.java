package dao;

import model.Customer;
import util.DBConnection;

import java.sql.*;
import java.util.Optional;

public class CustDAO {

    // Registration
    public void register(Customer customer) throws Exception {
        String sql = "INSERT INTO Customer(Username, Password, Full_name, Email, Phone) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getFullName());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getEmail());

            ps.executeUpdate();
        }
    }

    // View Customer Details
    public Optional<Customer> findByUsername(String username) throws Exception {
        String sql = "SELECT * FROM Customer WHERE Username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(rs.getInt("customer_id"));
                        customer.setUsername(rs.getString("username"));
                        customer.setPassword(rs.getString("password"));
                        customer.setFullName(rs.getString("full_name"));
                        customer.setEmail(rs.getString("email"));
                        customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime()
                        );
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }
}