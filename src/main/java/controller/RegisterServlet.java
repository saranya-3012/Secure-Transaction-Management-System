package controller;

import dao.UserDAO;
import model.User;
import util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

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
            if(!Validation.ValidName(name)) {
                res.getWriter().write("Name is required");
                return;
            }
            
            if(!Validation.ValidEmail(email)) {
                res.getWriter().write("Invalid Email");
                return;
            }
            
            if(!Validation.ValidPass(password)) {
            	res.getWriter().write("Password must be at least 8 characters");
                return;
            }    
            
            if(!Validation.Validrole(role)) {
            	res.getWriter().write("");
                return;
            } 

            // Hash Password
            String hashedPassword = Passwordhash.hashPassword(password);

            // Set User Object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setpassword(hashedPassword);
            user.setrole(role);

            // Call DAO
            UserDAO dao = new UserDAO();
            dao.register(user);

            // Success Response
            res.getWriter().write("User Registered Successfully");

        } 
        catch (Exception e) {
            res.getWriter().write(e.getMessage());
        }
    }
}
