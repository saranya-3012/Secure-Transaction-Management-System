package controller;

import dao.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int customerId = Integer.parseInt(req.getParameter("customerId"));
        BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(req.getParameter("balance")));

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(balance);

        try {
            accountDAO.create(account);
            resp.getWriter().println("Account Created Successfully");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}