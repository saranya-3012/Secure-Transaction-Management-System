package dao;

import model.Transaction;
import util.AppLogger;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Transaction Update
    public void transferMoney(int fromAccountId, int toAccountId, double amount) throws Exception {

        String senderSQL = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
        String receiverSQL = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
        String transactionSQL = "INSERT INTO Transactions(account_id, amount, type, total_amount) " +
                      "VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // Start transaction

            AppLogger.LOGGER.info("Transfer started: From " + fromAccountId + " To " + toAccountId);
            try (
                    PreparedStatement senderps = con.prepareStatement(senderSQL);
                    PreparedStatement receiverps = con.prepareStatement(receiverSQL);
                    PreparedStatement transactionps = con.prepareStatement(transactionSQL)
            ) {

                // 1️⃣ Deduct from sender
                senderps.setDouble(1, amount);
                senderps.setInt(2, fromAccountId);
                senderps.executeUpdate();

                // 2️⃣ Add to receiver
                receiverps.setDouble(1, amount);
                receiverps.setInt(2, toAccountId);
                receiverps.executeUpdate();

                // 3️⃣ Insert debit record
                AccountDAO accountdao = new AccountDAO();
                BigDecimal fromBalance = accountdao.findBalance(fromAccountId);
                BigDecimal newFromBalance = fromBalance.subtract(BigDecimal.valueOf(amount));

                transactionps.setInt(1, fromAccountId);
                transactionps.setDouble(2, amount);
                transactionps.setString(3, "DEBIT");
                transactionps.setBigDecimal(4, newFromBalance);
                transactionps.executeUpdate();

                // 4️⃣ Insert credit record
                BigDecimal toBalance = accountdao.findBalance(toAccountId);
                BigDecimal newToBalance = toBalance.add(BigDecimal.valueOf(amount));

                transactionps.setInt(1, toAccountId);
                transactionps.setDouble(2, amount);
                transactionps.setString(3, "CREDIT");
                transactionps.setBigDecimal(4, newToBalance);
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

    // Find Transaction history by Account ID
    public List<Transaction> findByAccountId(int accountId, int page) throws Exception {

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
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        }

        return list;
    }


    public void saveBatch(List<Transaction> transactions) throws Exception {

        String sql = "INSERT INTO Transactions(account_id, amount, type, total_amount) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            for (Transaction tx : transactions) {

                ps.setInt(1, tx.getAccountId());
                ps.setBigDecimal(2, tx.getAmount());
                ps.setString(3, tx.getType());
                ps.setBigDecimal(4, tx.getTotalAmount());

                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();

        } catch (Exception e) {
            throw new Exception("Batch insert failed", e);
        }
    }
    }
