package com.bank.controller;

import com.bank.dao.TransactionDAO;
import com.bank.util.ValidationUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TransactionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");

        try {

            // 1️⃣ Check Session (User must be logged in)
            HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("userId") == null) {
                res.getWriter().write("{\"error\":\"Please login first\"}");
                return;
            }

            int senderId = (int) session.getAttribute("userId");

            // 2️⃣ Get Parameters
            int receiverId = Integer.parseInt(req.getParameter("receiverId"));
            double amount = Double.parseDouble(req.getParameter("amount"));

            // 3️⃣ Validate Amount
            if (!ValidationUtil.isValidAmount(amount)) {
                res.getWriter().write("{\"error\":\"Invalid transfer amount\"}");
                return;
            }

            // 4️⃣ Call DAO Transfer Method
            TransactionDAO dao = new TransactionDAO();
            boolean success = dao.transfer(senderId, receiverId, amount);

            if (success) {
                res.getWriter().write("{\"message\":\"Transfer Successful\"}");
            } else {
                res.getWriter().write("{\"error\":\"Transfer Failed\"}");
            }

        } catch (NumberFormatException e) {
            res.getWriter().write("{\"error\":\"Invalid input format\"}");
        } catch (Exception e) {
            res.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
