package controller;

import dao.CustDAO;
import util.AppLogger;
import util.PasswordHash;
import util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
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

        AppLogger.LOGGER.info("Register request received");

        try {

            String hashedPassword = PasswordHash.hashPassword(password);

            CustDAO custdao = new CustDAO();
            custdao.register(username, hashedPassword, name, email, phone);
            resp.getWriter().println(name + " registered successfully");
            AppLogger.LOGGER.info(name + " registered successfully");
        }
        catch (ServletException e) {
            throw new ServletException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            AppLogger.LOGGER.severe("Error while registering customer: " + e.getMessage());
        }
    }
}
