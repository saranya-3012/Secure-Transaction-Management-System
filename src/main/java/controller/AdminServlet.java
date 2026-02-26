package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
        	
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().println("Access Denied");
            return;
        }

        resp.getWriter().println("Welcome Admin");
    }
}
