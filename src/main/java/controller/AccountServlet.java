package controller;

import dao.AccountDAO;
import model.Account;
import util.AppLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {

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
                AppLogger.LOGGER.info(String.format("New Account created with Account Number %s", accountNumber));
            }
        }
        catch (SQLException e) {
            throw new ServletException(e);
        }
        catch (IOException e) {
            throw new IOException(e);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        catch (Exception e) {
            AppLogger.LOGGER.severe((String.format("Error while creating new Account : %s ", e.getMessage())));
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {

        String action = req.getParameter("action");

        if ("view".equals(action)) {

            String username = req.getParameter("username");

            try {
                Account acc = (Account) AccountDAO.findByCustomerId(username);
                resp.getWriter().println(acc);
            } catch (SQLException e) {
                throw new ServletException(e);
            } catch (IOException e) {
                throw new IOException(e);
            } catch (Exception e) {
                AppLogger.LOGGER.severe((String.format("Error while get Account : " + e.getMessage())));
            }
        }
    }
}
