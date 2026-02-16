package com.bank.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        if (session != null && "ADMIN".equals(session.getAttribute("role"))) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().write("Access Denied (Admin Only)");
        }
    }
}
