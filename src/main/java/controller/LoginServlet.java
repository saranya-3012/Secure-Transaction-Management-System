package controller;

import dao.UserDAO;
import model.User;
import util.Passwordhash;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDAO dao = new UserDAO();
            User user = dao.login(email);

            if (user != null &&
                user.getpassword().equals(Passwordhash.hashPassword(password))) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getid());
                session.setAttribute("role", user.getrole());

                response.sendRedirect("dashboard.jsp");
            } 
            else {
                response.getWriter().println("Invalid credentials");
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
