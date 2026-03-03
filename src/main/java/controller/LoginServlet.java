package controller;

import dao.AdminDAO;
import dao.CustDAO;
import model.Admin;
import model.Customer;
import util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AdminDAO adminDAO = new AdminDAO();
    private final CustDAO customerDAO = new CustDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        HttpSession session = req.getSession();

        try {
            if ("admin".equalsIgnoreCase(role)) {

                Optional<Admin> admin = adminDAO.findByUsername(username);

                if (admin.isPresent() && admin.get().getPassword().equals(password)) {

                    session.setAttribute("userId", admin.get().getAdminId());
                    session.setAttribute("role", "ADMIN");
                    resp.getWriter().println("Admin Login Successful");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Admin Credentials");
                }

            }
            else {

                Optional<Customer> customer = customerDAO.findByUsername(username);

                if (customer.isPresent()) {

                    String hashedpassword = PasswordHash.hashPassword(password);

                    if (customer.get().getPassword().equals(hashedpassword)) {

                        session.setAttribute("userId", customer.get().getCustomerId());
                        session.setAttribute("role", "CUSTOMER");
                        resp.getWriter().println("Customer Login Successful");

                    } else {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                "Invalid Customer Credentials");
                    }

                } else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid Customer Credentials");
                }
            }

        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
