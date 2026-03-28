package service;

import dao.*;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import java.util.Optional;

public class AuthService {

    private static final AdminDAO adminDAO = new AdminDAO();

    public static String loginAdmin(String username, String password) throws Exception {

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

    public static String loginCustomer(String username, String password) throws Exception {

        Optional<Customer> customer = CustDAO.findByUsername(username);

        if (customer.isPresent()) {
            String storedPassword = customer.get().getPassword();
            String enteredPassword = PasswordHash.hashPassword(password);

            if (enteredPassword.equals(storedPassword)) {
                CustDAO.findByUsername(username);
                return username + " login Successfully!";
            }
        }
        return "";
    }
}