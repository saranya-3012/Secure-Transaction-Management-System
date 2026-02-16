package com.bank.controller;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import com.bank.util.PasswordUtil;

import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String email = req.getParameter("email");
        String pwd = req.getParameter("password");

        try {
            String hashedPwd = PasswordUtil.hash(pwd);
            User user = new UserDAO().authenticate(email, hashedPwd);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("role", user.getRole());
                res.getWriter().write("Login Success");
            } else {
                res.getWriter().write("Invalid Credentials");
            }

        } catch (Exception e) {
            res.getWriter().write("Login Error: " + e.getMessage());
        }
    }
}
