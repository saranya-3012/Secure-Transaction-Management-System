package com.bank.controller;

import com.bank.dao.UserDAO;
import com.bank.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("application/json");

        try {

            // 1️⃣ Check session
            HttpSession session = req.getSession(false);

            if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
                res.getWriter().write("{\"error\":\"Admin access required\"}");
                return;
            }

            // 2️⃣ Pagination parameters
            int page = Integer.parseInt(req.getParameter("page"));
            int size = Integer.parseInt(req.getParameter("size"));

            UserDAO dao = new UserDAO();
            List<User> users = dao.fetchUsers(page, size);

            // 3️⃣ Build JSON response
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (User u : users) {
                json.append("{")
                        .append("\"id\":").append(u.getId()).append(",")
                        .append("\"name\":\"").append(u.getName()).append("\",")
                        .append("\"email\":\"").append(u.getEmail()).append("\",")
                        .append("\"role\":\"").append(u.getRole()).append("\",")
                        .append("\"status\":\"").append(u.getStatus()).append("\"")
                        .append("},");
            }

            if (users.size() > 0)
                json.deleteCharAt(json.length() - 1);

            json.append("]");

            res.getWriter().write(json.toString());

        } catch (Exception e) {
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Freeze / Activate user
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("application/json");

        try {

            HttpSession session = req.getSession(false);

            if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
                res.getWriter().write("{\"error\":\"Admin access required\"}");
                return;
            }

            int userId = Integer.parseInt(req.getParameter("userId"));
            String action = req.getParameter("action"); // FREEZE or ACTIVATE

            UserDAO dao = new UserDAO();

            if ("FREEZE".equalsIgnoreCase(action)) {
                dao.updateStatus(userId, "FROZEN");
                res.getWriter().write("{\"message\":\"Account Frozen\"}");
            } else if ("ACTIVATE".equalsIgnoreCase(action)) {
                dao.updateStatus(userId, "ACTIVE");
                res.getWriter().write("{\"message\":\"Account Activated\"}");
            } else {
                res.getWriter().write("{\"error\":\"Invalid action\"}");
            }

        } catch (Exception e) {
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
