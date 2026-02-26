package controller;

import dao.CustDAO;
import model.Customer;
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
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");

            // Input Validation             
            Validation.isValidUsername(name);
            Validation.isValidEmail(email);              
            Validation.isValidPassword(password);
            Validation.isValidPhone(phone);
            
            // Set User Object
            Customer user = new Customer();
            user.setName(name);
            user.setEmail(email);
            user.setpassword(password);

            // Call DAO
            CustDAO dao = new CustDAO();
            dao.register(user);

            res.getWriter().write("User Registered Successfully");

        } 
        catch (Exception e) {
            res.getWriter().write(e.getMessage());
        }
    }
}