package service;

import dao.*;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import java.util.Optional;

public class AuthService {

    private static final AdminDAO adminDAO = new AdminDAO();
    private static final CustDAO custDAO = new CustDAO();
    private static String rawpassword;

    public static String loginAdmin(String username, String password) throws Exception {

        Optional<Admin> admin = adminDAO.findByUsername(username);

        String enteredPassword = PasswordHash.hashPassword(rawpassword);

        if (admin.isPresent() && enteredPassword.equals(rawpassword)){
            return "Admin login Successfully!";
        }
        return "Not login";
    }

    public static String loginCustomer(String username, String password) throws Exception {

        Optional<Customer> customer = custDAO.findByUsername(username);

        String enteredPassword = PasswordHash.hashPassword(rawpassword);

        if (customer.isPresent() && enteredPassword.equals(rawpassword)) {
            return "Customer login Successfully!";
        }
        return "Not login";
    }
}
