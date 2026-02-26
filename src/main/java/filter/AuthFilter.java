package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Allow login and register without session
        if (uri.contains("login") || uri.contains("register")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        // If not logged in
        if (session == null || session.getAttribute("username") == null) {
            res.getWriter().println("Please Login First");
            return;
        }

        // Role check for admin pages
        if (uri.contains("/admin")) {
            String role = (String) session.getAttribute("role");

            if (!"ADMIN".equals(role)) {
                res.getWriter().println("Access Denied - Admin Only");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}