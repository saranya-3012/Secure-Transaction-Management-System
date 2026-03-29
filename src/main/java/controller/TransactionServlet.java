package controller;

import dao.TransactionDAO;
import model.Transaction;
import service.TransactionService;
import util.AppLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/transfer")
public class TransactionServlet extends HttpServlet {

    private final TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            catch (ServletException e) {
                throw new ServletException(e);
            }
            catch (NumberFormatException e) {
                throw new NumberFormatException();
            }
            catch (Exception e) {
                throw new IOException(e);
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if("view".equals(action)){
            HttpSession session = req.getSession(false);

            try {

                int accountId = (int) session.getAttribute("accountId");
                int pageno = Integer.parseInt(req.getParameter("pageNo"));
                
                List<Transaction> transactions =
                        transactionDAO.findByAccountId(accountId, pageno);

                req.setAttribute("transactions", transactions);
                AppLogger.LOGGER.severe("Transaction details viewed ");

            }
            catch (Exception e) {
                throw new IOException(e);
            }

        }


    }
}
