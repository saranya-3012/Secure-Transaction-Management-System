package service;

import dao.*;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import java.util.Optional;

public class AuthService {

    private final AdminDAO adminDAO = new AdminDAO();
    private final CustDAO custDAO = new CustDAO();

    public Optional<Admin> loginAdmin(String username, String password) throws Exception {

        Optional<Admin> admin = adminDAO.findByUsername(username);

        if (admin.isPresent()) {
            String hashed = PasswordHash.hashPassword(password);

            if (admin.get().getPassword().equals(hashed)) {
                return admin;
            }
        }

        return Optional.empty();
    }

    public Optional<Customer> loginCustomer(String username, String password) throws Exception {

        Optional<Customer> customer = custDAO.findByUsername(username);

        if (customer.isPresent()) {
            String hashed = PasswordHash.hashPassword(password);

            if (customer.get().getPassword().equals(hashed)) {
                return customer;
            }
        }

        return Optional.empty();
    }
}