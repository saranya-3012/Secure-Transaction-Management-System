package controller;

import dao.AccountDAO;
import model.Account;
import util.AppLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {

            int customerId = Integer.parseInt(req.getParameter("customerId"));
            String accountno = req.getParameter("AccountNo");
            BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(req.getParameter("balance")));

            Account account = new Account();
            account.setCustomerId(customerId);
            account.setBalance(balance);
            account.setAccountNumber(accountno);

            try {
                accountDAO.create(account);
                resp.getWriter().println("Account Created Successfully");
                AppLogger.LOGGER.info( " New Account created with Account Number " + accountno);
            }
            catch (ServletException e) {
                throw new ServletException(e);
            }
            catch (IOException e) {
                throw new IOException(e);
            }
            catch (Exception e) {
                AppLogger.LOGGER.severe("Error while creating new Account : " + e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("view".equals(action)) {

            String username = req.getParameter("username");

            try {
                Account acc = (Account) AccountDAO.findByCustomerId(username);
                resp.getWriter().println(acc);
            }
            catch (ServletException e) {
                throw new ServletException(e);
            }
            catch (IOException e) {
                throw new IOException(e);
            }
            catch (Exception e) {
                AppLogger.LOGGER.severe("Error while get Account : " + e.getMessage());
            }
        }
    }
}