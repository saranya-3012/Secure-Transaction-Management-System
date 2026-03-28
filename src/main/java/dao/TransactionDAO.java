package dao;

import model.Transaction;
import util.AppLogger;
import dbconfiguration.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void transferMoney(int fromAccountId, int toAccountId, double amount) throws Exception {

        String senderSQL = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
        String receiverSQL = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
        String transactionSQL = "INSERT INTO Transactions(account_id, amount, type, total_amount, Status) " +
                "VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            AppLogger.LOGGER.info("Transfer started: From " + fromAccountId + " To " + toAccountId);
            try (
                    PreparedStatement senderps = con.prepareStatement(senderSQL);
                    PreparedStatement receiverps = con.prepareStatement(receiverSQL);
                    PreparedStatement transactionps = con.prepareStatement(transactionSQL)
            ) {

                senderps.setDouble(1, amount);
                senderps.setInt(2, fromAccountId);
                int senderUpdated = senderps.executeUpdate();

                receiverps.setDouble(1, amount);
                receiverps.setInt(2, toAccountId);
                int receiverUpdated = receiverps.executeUpdate();

                String status = (senderUpdated == 1 && receiverUpdated == 1) ? "Success" : "Failed";

                AccountDAO accountdao = new AccountDAO();
                BigDecimal fromBalance = accountdao.findBalance(fromAccountId);
                BigDecimal toBalance = accountdao.findBalance(toAccountId);

                transactionps.setInt(1, fromAccountId);
                transactionps.setDouble(2, amount);
                transactionps.setString(3, "DEBIT");
                transactionps.setBigDecimal(4, fromBalance);
                transactionps.setString(5, status);
                transactionps.executeUpdate();

                transactionps.setInt(1, toAccountId);
                transactionps.setDouble(2, amount);
                transactionps.setString(3, "CREDIT");
                transactionps.setBigDecimal(4, toBalance);
                transactionps.setString(5, status);
                transactionps.executeUpdate();

                con.commit();

                AppLogger.LOGGER.info("Transfer completed successfully");
            }
            catch (Exception e) {
                con.rollback();
                AppLogger.LOGGER.severe("Transaction failed: " + e.getMessage());
            }
        }
    }


    public List<Transaction> findByAccountId(int accountId, int page) {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM Transactions WHERE account_id = ? ORDER BY created_at DESC LIMIT 5 OFFSET ?";

        int offset = (page - 1) * 5;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("account_id"),
                        rs.getBigDecimal("amount"),
                        rs.getString("type"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }

        }
        catch (Exception e) {
            AppLogger.LOGGER.severe("Error while getting Transaction details : " + e.getMessage());
        }
        return list;

    }


    public void saveBatch(List<Transaction> transactions) throws Exception {
        Connection con = DBConnection.getConnection();
        try {

            con.setAutoCommit(false);

            String sql = "INSERT INTO Transactions(account_id, amount, type, total_amount, status) VALUES (?, ?, ?, ?, 'Success')";
            PreparedStatement ps = con.prepareStatement(sql);

            int count = 0;
            int batchSize = 10;

            for (Transaction tx : transactions) {

                ps.setInt(1, tx.getAccountId());
                ps.setBigDecimal(2, tx.getAmount());
                ps.setString(3, tx.getType());
                ps.setBigDecimal(4, tx.getTotalAmount());
                ps.addBatch();
                count++;

                if (count % batchSize == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();

            con.commit();

        } catch (Exception e) {
            if (con != null) con.rollback();
            throw new Exception("Batch insert failed", e);
        }
    }
}