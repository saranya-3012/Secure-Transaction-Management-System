package service;

import dao.*;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import java.util.Optional;

public class AuthService {

    private static final AdminDAO adminDAO = new AdminDAO();
    private static final CustDAO custDAO = new CustDAO();
    private static String enteredpassword;

    public static String loginAdmin(String username, String password) throws Exception {

        Optional<Admin> admin = adminDAO.findByUsername(username);

        String storedPassword = PasswordHash.hashPassword(enteredpassword);

        if (admin.isPresent() && storedPassword.equals(enteredpassword)){
            return "Admin login Successfully!";
        }
        return "Not login";
    }

    public static String loginCustomer(String username, String password) throws Exception {

        Optional<Customer> customer = custDAO.findByUsername(username);

        String storedPassword = PasswordHash.hashPassword(enteredpassword);

        if (customer.isPresent() && storedPassword.equals(enteredpassword)) {
            return "Admin login Successfully!";
        }
        return "Not login";
    }
}
