package controller;

import dao.TransactionDAO;
import model.Transaction;
import service.TransactionService;
import util.AppLogger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/transfer")
public class TransactionServlet extends HttpServlet {

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private static final String WRITE_ERROR = "Failed to write response: ";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if("create".equals(action)){

            HttpSession session = req.getSession(false);

            try {
                int fromAccount = (int) session.getAttribute("accountId");
                int toAccount = Integer.parseInt(req.getParameter("toAccount"));
                double amount = Double.parseDouble(req.getParameter("amount"));

                if (amount <= 0 || amount >= 25000) {
                    resp.getWriter().println("Invalid amount");
                }

                TransactionService.transfer(fromAccount, toAccount, amount);
            }
            catch (NumberFormatException e) {
                try {
                    resp.getWriter().println("Invalid numeric input: " + e.getMessage());
                } catch (IOException ioEx) {
                    AppLogger.LOGGER.severe(WRITE_ERROR + ioEx.getMessage());
                }
                AppLogger.LOGGER.warning("Failed to parse account or amount: " + e.getMessage());
            } catch (Exception e) {
                try {
                    resp.getWriter().println("Transaction failed: " + e.getMessage());
                } catch (IOException ioEx) {
                    AppLogger.LOGGER.severe(WRITE_ERROR + ioEx.getMessage());
                }
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        String action = req.getParameter("action");

        if("view".equals(action)){
            HttpSession session = req.getSession(false);

            try {

                int accountId = (int) session.getAttribute("accountId");
                int pageNo = Integer.parseInt(req.getParameter("pageNo"));

                List<Transaction> transactions =
                        transactionDAO.findByAccountId(accountId, pageNo);

                req.setAttribute("transactions", transactions);
                AppLogger.LOGGER.severe("Transaction details viewed ");

            }
            catch (Exception e) {
                AppLogger.LOGGER.severe("Error while fetching transactions: " + e.getMessage());
                try {
                    resp.getWriter().println("Unable to process your request.");
                } catch (IOException ioEx) {
                    AppLogger.LOGGER.severe(WRITE_ERROR + ioEx.getMessage());
                }
            }

        }
    }
}
