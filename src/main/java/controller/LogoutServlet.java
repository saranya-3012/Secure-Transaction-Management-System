package com.bank.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();

        res.getWriter().write("Logged Out Successfully");
    }
}
