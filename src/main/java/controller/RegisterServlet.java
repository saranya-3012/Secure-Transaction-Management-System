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

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (!Validation.isValidUsername(username)) {
            resp.getWriter().println("Enter valid Username!");
        }

        if (!Validation.isValidPassword(password)){
            resp.getWriter().println("Enter valid Password!");
        }

        if (!Validation.isValidEmail(email)){
            resp.getWriter().println("Enter valid Email!");
        }

        if (!Validation.isValidPhone(phone)) {
            resp.getWriter().println("Enter valid Phone number!");
        }

        String hashedPassword = PasswordHash.hashPassword(password);

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(hashedPassword);
        customer.setEmail(email);
        customer.setPhone(phone);

        CustDAO custdao = new CustDAO();
        try {
            custdao.register(customer);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

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
