package dao;

import model.Customer;
import util.DBConnection;

import java.sql.*;
import java.util.Optional;

public class CustDAO {

    // Registration
    public void register(String username, String password, String name, String email, String phone) throws Exception {
        String sql = "INSERT INTO Customer(Username, Password, Full_name, Email, Phone, Role) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, "Customer");

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
                        customer.setUsername(rs.getString("username"));
                        customer.setPassword(rs.getString("password"));
                        customer.setFullName(rs.getString("full_name"));
                        customer.setEmail(rs.getString("email"));

                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }
}
