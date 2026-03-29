package service;

import dao.*;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

    private static final AdminDAO adminDAO = new AdminDAO();

    private AuthService() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String loginAdmin(String username, String password) throws SQLException {

        try {
            Optional<Admin> admin = adminDAO.findByUsername(username);

            if (admin.isPresent()) {
                String storedPassword = admin.get().getPassword();
                String enteredPassword = PasswordHash.hashPassword(password);

                if (enteredPassword.equals(storedPassword)) {
                    return username + " login Successfully!";
                }
            }
            return "";
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while fetching user", e);
        }
    }


    public static String loginCustomer(String username, String password) throws SQLException {

        try {
            Optional<Customer> customer = CustomerDAO.findByUsername(username);

            if (customer.isPresent()) {
                String storedPassword = customer.get().getPassword();
                String enteredPassword = PasswordHash.hashPassword(password);

                if (enteredPassword.equals(storedPassword)) {
                    CustomerDAO.findByUsername(username);
                    return username + " login Successfully!";
                }
            }
            return "";
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while fetching user", e);
        }
    }
}
