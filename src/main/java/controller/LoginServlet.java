package controller;

import service.AuthService;
import util.AppLogger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            HttpSession session = req.getSession(true);

            if ("admin".equalsIgnoreCase(role)) {

                String login = AuthService.loginAdmin(username, password);
                resp.getWriter().println(login);

                session.setAttribute("username", username);
                session.setAttribute("role", "ADMIN");
                AppLogger.LOGGER.log(Level.INFO, "{0} logged as ADMIN", username);

            }
            else if ("customer".equalsIgnoreCase(role)) {

                String login = AuthService.loginCustomer(username, password);
                resp.getWriter().println(login);

                session.setAttribute("username", username);
                session.setAttribute("role", "CUSTOMER");
                AppLogger.LOGGER.log(Level.INFO, "{0} logged as CUSTOMER", username);

            }
            else {
                resp.getWriter().println("Role must be either 'admin' or 'customer'");
            }
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            AppLogger.LOGGER.severe("Error during login process: " + e.getMessage());
        }
    }
}
