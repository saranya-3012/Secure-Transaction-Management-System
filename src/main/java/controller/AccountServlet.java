package controller;

import dao.AccountDAO;
import model.Account;
import util.AppLogger;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException {

        String action = req.getParameter("action");

        try {
            if ("create".equals(action)) {

                int customerId = Integer.parseInt(req.getParameter("customerId"));
                String accountNumber = req.getParameter("AccountNo");
                BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(req.getParameter("balance")));

                Account account = new Account();
                account.setCustomerId(customerId);
                account.setBalance(balance);
                account.setAccountNumber(accountNumber);

                accountDAO.create(account);
                resp.getWriter().println("Account Created Successfully");
                AppLogger.LOGGER.log(Level.INFO, "New Account created with Account Number {0}", accountNumber);
            }
        } catch (NumberFormatException e) {
            AppLogger.LOGGER.warning("Failed to parse numeric input: " + e.getMessage());
            try {
                resp.getWriter().println("Invalid number format. Please enter valid numeric values.");
            } catch (IOException ioEx) {
                AppLogger.LOGGER.severe("Failed to write response: " + ioEx.getMessage());
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            AppLogger.LOGGER.severe("IOException in AccountServlet: " + e.getMessage());
        } catch (SQLException e) {
            AppLogger.LOGGER.severe("Database error while creating account: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException {

        String action = req.getParameter("action");

        if ("view".equals(action)) {

            String username = req.getParameter("username");

            try {
                java.util.List<Account> accounts = AccountDAO.findByCustomerId(username);
                resp.getWriter().println(accounts);
            } catch (SQLException e) {
                AppLogger.LOGGER.severe("Database error: " + e.getMessage());
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                try {
                    resp.getWriter().println("Unable to fetch account details");
                } catch (IOException ioEx) {
                    AppLogger.LOGGER.severe("Write failed: " + ioEx.getMessage());
                }
            } catch (Exception e) {
                AppLogger.LOGGER.log(Level.SEVERE, "Error while get Account: {0}", e.getMessage());
            }
            }
    }
}
