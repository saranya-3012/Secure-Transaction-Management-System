package dao;

import model.Transaction;
import util.AppLogger;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Save the Transaction Detail
    public void save(Transaction transaction) throws Exception {

        String sql = "INSERT INTO Transactions(Account_id, Amount, Type, Total_Amount) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaction.getAccountId());
            ps.setBigDecimal(2, transaction.getAmount());
            ps.setString(3, transaction.getType());
            ps.setBigDecimal(4, transaction.getTotalAmount());

            ps.executeUpdate();
        }
    }

    // Find Transaction history by Account ID
    public List<Transaction> findByAccountId(
            int accountId,
            int page,
            int size) throws Exception {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM Transactions " +
                "WHERE account_id = ? " +
                "ORDER BY created_at DESC " +
                "LIMIT ? OFFSET ?";

        int offset = (page - 1) * size;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.setInt(2, size);
            ps.setInt(3, offset);

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

    // Transaction Update
    public void transferMoney(int fromAccountId, int toAccountId, double amount) throws Exception {

        String deductSQL = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
        String addSQL = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
        String insertSQL = "INSERT INTO Transactions(account_id, amount, type, total_amount) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // Start transaction

            AppLogger.LOGGER.info("Transfer started: From " + fromAccountId + " To " + toAccountId);
            try (
                    PreparedStatement deductps = con.prepareStatement(deductSQL);
                    PreparedStatement addps = con.prepareStatement(addSQL);
                    PreparedStatement insertps = con.prepareStatement(insertSQL)
            ) {

                // 1️⃣ Deduct from sender
                deductps.setDouble(1, amount);
                deductps.setInt(2, fromAccountId);
                deductps.executeUpdate();

                // 2️⃣ Add to receiver
                addps.setDouble(1, amount);
                addps.setInt(2, toAccountId);
                addps.executeUpdate();

                // 3️⃣ Insert debit record
                AccountDAO accountdao = new AccountDAO();
                BigDecimal fromBalance = accountdao.findBalance(fromAccountId);
                BigDecimal newFromBalance = fromBalance.subtract(BigDecimal.valueOf(amount));

                insertps.setInt(1, fromAccountId);
                insertps.setDouble(2, amount);
                insertps.setString(3, "DEBIT");
                insertps.setBigDecimal(4, newFromBalance);
                insertps.executeUpdate();

                // 4️⃣ Insert credit record
                BigDecimal toBalance = accountdao.findBalance(toAccountId);
                BigDecimal newToBalance = fromBalance.add(BigDecimal.valueOf(amount));

                insertps.setInt(1, toAccountId);
                insertps.setDouble(2, amount);
                insertps.setString(3, "CREDIT");
                insertps.setBigDecimal(4, newToBalance);
                insertps.executeUpdate();

                con.commit();

                AppLogger.LOGGER.info("Transfer completed successfully");
            }
            catch (Exception e) {
                con.rollback();
                AppLogger.LOGGER.severe("Transaction failed: " + e.getMessage());
            }
            }
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
