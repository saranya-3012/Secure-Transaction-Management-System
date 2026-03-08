package controller;

import dao.AdminDAO;
import dao.CustDAO;
import model.Admin;
import model.Customer;
import service.AuthService;
import util.AppLogger;
import util.Validation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (!Validation.isValidUsername(username)) {
            resp.getWriter().println("Enter valid Username!");
        }

        if (!Validation.isValidPassword(password)) {
            resp.getWriter().println("Enter valid Password!");
        }

        try {
            if ("admin".equalsIgnoreCase(role)) {
                String login = AuthService.loginAdmin(username, password);

                resp.getWriter().println(login);
                AdminDAO adminDAO = new AdminDAO();
                Optional<Admin> getAdminData = adminDAO.findByUsername(username);

                if (getAdminData.isPresent()) {

                    AppLogger.LOGGER.info(username + " logged ");
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userId", getAdminData.get().getAdminId());
                    session.setAttribute("role", "ADMIN");

                }
            }

            else if ("customer".equalsIgnoreCase(role)) {
                String login = AuthService.loginCustomer(username, password);

                resp.getWriter().println(login);
                CustDAO customerDAO = new CustDAO();
                Optional<Customer> getCustomerData = customerDAO.findByUsername(username);

                if (getCustomerData.isPresent()) {

                    AppLogger.LOGGER.info(username + " logged ");
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userId", getCustomerData.get().getCustomerId());
                    session.setAttribute("role", "CUSTOMER");

                }
            }

            else if (role.isEmpty()) {
                resp.getWriter().println("Role must be either 'admin' or 'customer'");
            }

            else {
                resp.getWriter().println("Invalid credentials");
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
