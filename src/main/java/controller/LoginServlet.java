package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import util.PasswordHash;
import util.CheckPassword;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            String hashpass = PasswordHash.hashPassword(password);
            
            if (hashpass != null && hashpass.equals(CheckPassword.getPassword(email))) {

                HttpSession session = request.getSession();
                session.setAttribute("email", email);

                response.getWriter().println("Login Success");

            } 
            else {
                response.getWriter().println("Invalid Credentials");
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}