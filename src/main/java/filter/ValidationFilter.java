package filter;

import util.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/register") 
public class ValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setContentType("text/plain");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (!Validation.isValidUsername(username)) {
            resp.getWriter().println("Enter valid Username!");
            return;
        }

        if (!Validation.isValidPassword(password)) {
            resp.getWriter().println("Enter valid Password!");
            return;
        }

        if (!Validation.isValidEmail(email)) {
            resp.getWriter().println("Enter valid Email!");
            return;
        }

        if (!Validation.isValidPhone(phone)) {
            resp.getWriter().println("Enter valid Phone number!");
            return;
        }

        chain.doFilter(request, response);
    }
}