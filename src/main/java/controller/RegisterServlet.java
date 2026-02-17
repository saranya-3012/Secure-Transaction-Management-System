package controller;

import dao.UserDAO;
import model.User;
import util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");

        try {
            // Get parameters from Postman
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role = req.getParameter("role");  // ADMIN or USER
            

            // Input Validation             
            Validation.isValidUsername(name);
            Validation.isValidEmail(email);              
            Validation.isValidPassword(password);
            
            // Set User Object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setpassword(password);

            // Call DAO
            UserDAO dao = new UserDAO();
            dao.register(user);

            res.getWriter().write("User Registered Successfully");

        } 
        catch (Exception e) {
            res.getWriter().write(e.getMessage());
        }
    }
}
