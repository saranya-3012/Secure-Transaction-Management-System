package controller;

import dao.TransactionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/customer/transfer")
public class TransactionServlet extends HttpServlet {

    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int fromAccount = Integer.parseInt(req.getParameter("fromAccount"));
        int toAccount = Integer.parseInt(req.getParameter("toAccount"));
        double amount = Double.parseDouble(req.getParameter("amount"));

        try {
            transactionDAO.transferMoney(fromAccount, toAccount, amount);
            resp.getWriter().println("Transaction Successful");
        }
        catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Transaction Failed");
        }
    }
}


@WebServlet("/customer/transactions")
public class TransactionServlet extends HttpServlet {

    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int accountId = Integer.parseInt(req.getParameter("accountId"));

        int page = 1;
        int size = 5;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        try {
            var transactions =
                    transactionDAO.findByAccountId(accountId, page, size);

            req.setAttribute("transactions", transactions);
            req.getRequestDispatcher("/customer/transactionHistory.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}