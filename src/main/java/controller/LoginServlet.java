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
            HttpSession session = req.getSession(true);

            if ("admin".equalsIgnoreCase(role)) {

                String login = AuthService.loginAdmin(username, password);
                resp.getWriter().println(login);

                AdminDAO adminDAO = new AdminDAO();
                Optional<Admin> getAdminData = adminDAO.findByUsername(username);

                if (getAdminData.isPresent()) {

                    AppLogger.LOGGER.info(username + " logged as ADMIN");

                    session.setAttribute("userId", getAdminData.get().getAdminId());
                    session.setAttribute("role", "ADMIN");

                    resp.getWriter().println("-----ADMIN dashboard-----");
                    resp.getWriter().println("MENU----");
                    resp.getWriter().println("1. Create new Customer Account");
                    resp.getWriter().println("2. View Customer details");
                    resp.getWriter().println("3. New Transaction");
                    resp.getWriter().println("4. View Transactions details");
                    String option = req.getParameter("option");

                    switch(option) {
                        case "1":
                            resp.sendRedirect("/account?action=create");
                            break;

                        case "2":
                            resp.sendRedirect("/account?action=view");
                            break;
                        case "3":
                            resp.sendRedirect("/transfer?action=create");
                            break;
                        case "4":
                            resp.sendRedirect("/transfer?action=view");
                            break;
                    }
                } else {
                    resp.getWriter().println("Access Denied!");
                    AppLogger.LOGGER.severe("Admin access login incorrect");
                }
            }

            else if ("customer".equalsIgnoreCase(role)) {

                String login = AuthService.loginCustomer(username, password);
                resp.getWriter().println(login);

                Optional<Customer> getCustomerData = CustDAO.findByUsername(username);

                if (getCustomerData.isPresent()) {

                    AppLogger.LOGGER.info(username + " logged as CUSTOMER");

                    session.setAttribute("userId", getCustomerData.get().getCustomerId());
                    session.setAttribute("role", "CUSTOMER");
                }
                else {
                    resp.getWriter().println("Access Denied!");
                    AppLogger.LOGGER.severe("Customer access login incorrect");
                }
            }
            else {
                resp.getWriter().println("Role must be either 'admin' or 'customer'");
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}