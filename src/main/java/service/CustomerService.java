package service;

import dao.CustDAO;
import model.Customer;
import util.PasswordHash;

public class CustomerService {

    private final CustDAO custDAO = new CustDAO();

    public void register(Customer customer) throws Exception {

        String hashed = PasswordHash.hashPassword(customer.getPassword());
        customer.setPassword(hashed);

        custDAO.register(customer);
    }
}