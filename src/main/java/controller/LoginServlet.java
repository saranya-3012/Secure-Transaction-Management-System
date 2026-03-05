package controller;

import dao.AdminDAO;
import dao.CustDAO;
import model.Admin;
import model.Customer;
import service.AuthService;
import util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (!Validation.isValidUsername(username)) {
            resp.getWriter().println("Enter valid Username!");
        }

        if (!Validation.isValidPassword(password)){
            resp.getWriter().println("Enter valid Password!");
        }

        HttpSession session = req.getSession();

        try {
            private final AdminDAO adminDAO = new AdminDAO();
            private final CustDAO customerDAO = new CustDAO();
            private static final AuthService authService = new AuthService();
            
            if ("admin".equalsIgnoreCase(role)) {
                authService.loginAdmin(username, password);
            }
            else if ("customer".equalsIgnoreCase(role)) {
                authService.loginCustomer(username, password);
            }
            else if (role.isEmpty()) {

                Optional<Admin> admin = authService.loginAdmin(username, password);
                if (admin.isPresent()) {
                    session.setAttribute("userId", admin.get().getAdminId());
                    session.setAttribute("role", "ADMIN");
                    resp.getWriter().println("Admin Login Successful");
                    return;
                }

                    session.setAttribute("userId", admin.get().getAdminId());
                    session.setAttribute("role", "ADMIN");
                    resp.getWriter().println("Admin Login Successful");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Admin Credentials");
                }

            } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
            else if ("customer".equalsIgnoreCase(role)) {

                Optional<Customer> customer = customerDAO.findByUsername(username);

                if (customer.isPresent()) {

                    session.setAttribute("userId", customer.get().getCustomerId());
                    session.setAttribute("role", "CUSTOMER");
                    resp.getWriter().println("Customer Login Successful");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid Customer Credentials");
                }
            }
            else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Role must be either 'admin' or 'customer'");
            }


        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
