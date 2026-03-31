package controller;

import dao.CustomerDAO;
import util.AppLogger;
import util.PasswordHash;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.logging.Level;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        AppLogger.LOGGER.info("Register request received");

        try {

            String hashedPassword = PasswordHash.hashPassword(password);

            CustomerDAO customerDao = new CustomerDAO();
            customerDao.register(username, hashedPassword, name, email, phone);
            resp.getWriter().println(name + " registered successfully");
            AppLogger.LOGGER.log(Level.INFO, "{0} registered successfully", username);     
        }
        catch (Exception e) {
            AppLogger.LOGGER.severe(String.format("Error while registering customer: %s ", e.getMessage()));
        }
    }
}
