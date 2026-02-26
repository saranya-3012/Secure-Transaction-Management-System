package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.*;
import util.*;
import model.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            String hashpass = PasswordHash.hashPassword(password);
            
            CustDAO dao = new CustDAO();
            Customer cust = dao.login(username, password);
            
            if (hashpass != null && hashpass.equals(CheckPassword.getPassword(username))) {

                HttpSession session = request.getSession();
                session.setAttribute("cust_id", cust.getcust_id());
                session.setAttribute("role", cust.getrole());
                
                AppLogger.LOGGER.info("User logged in: " + username);
                response.getWriter().println("Login Success");

            } 
            else {
                response.getWriter().println("Invalid Credentials");
            }

        } 
        catch (Exception e) {
        	response.getWriter().println("Error");
        }
    }
}