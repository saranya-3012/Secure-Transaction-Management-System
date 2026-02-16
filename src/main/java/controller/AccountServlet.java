package com.bank.controller;

import com.bank.dao.UserDAO;
import com.bank.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("application/json");

        try {

            HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("userId") == null) {
                res.getWriter().write("{\"error\":\"Please login first\"}");
                return;
            }

            int userId = (int) session.getAttribute("userId");

            UserDAO dao = new UserDAO();
            User user = dao.getUserById(userId);

            if (user == null) {
                res.getWriter().write("{\"error\":\"User not found\"}");
                return;
            }

            String json = "{"
                    + "\"id\":" + user.getId() + ","
                    + "\"name\":\"" + user.getName() + "\","
                    + "\"email\":\"" + user.getEmail() + "\","
                    + "\"balance\":" + user.getBalance() + ","
                    + "\"status\":\"" + user.getStatus() + "\""
                    + "}";

            res.getWriter().write(json);

        } catch (Exception e) {
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
