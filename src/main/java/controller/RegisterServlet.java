package controller;

import dao.CustDAO;
import model.Customer;
import util.AppLogger;
import util.PasswordHash;
import util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final CustDAO customerDAO = new CustDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (!Validation.isValidUsername(username) || !Validation.isValidPassword(password) ||
                !Validation.isValidEmail(email) || !Validation.isValidPhone(phone)) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Input Data");
            return;
        }

        String hashedPassword = PasswordHash.hashPassword(password);

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(hashedPassword);
        customer.setEmail(email);
        customer.setPhone(phone);

        AppLogger.LOGGER.info("Register request received");
        try {
            customerDAO.register(customer);
            AppLogger.LOGGER.info("Customer registered successfully");
        }
        catch (Exception e) {
            AppLogger.LOGGER.severe("Error while registering customer: " + e.getMessage());
        }
    }
}
