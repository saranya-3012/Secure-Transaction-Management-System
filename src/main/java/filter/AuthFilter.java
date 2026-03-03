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
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        // Allow login and register without session
        if (path.contains("login") || path.contains("register")) {
            chain.doFilter(request, response);
            return;
        }

        // If no session → block
        if (session == null || session.getAttribute("role") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login first");
            return;
        }

        String role = (String) session.getAttribute("role");

        // Admin-only endpoints
        if (path.contains("/admin") && !"ADMIN".equals(role)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        // Customer-only endpoints
        if (path.contains("/customer") && !"CUSTOMER".equals(role)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        chain.doFilter(request, response);
    }
}
