package dao;

import model.Transaction;
import util.DBConnection;
import java.util.*;
import java.math.BigDecimal;
import java.rmi.AccessException;
import java.sql.*;

public class TransactionDAO {

    public void newTransaction(Transaction user) throws Exception {
    	
    // Insert New Transaction Detail

        String sql = "INSERT INTO Transaction (Account_ID, Type, Amount) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        	ps.setLong(1, user.getacc_id());
            ps.setString(2, user.gettype());
            ps.setBigDecimal(3, user.getamount());

            ps.executeUpdate();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    

    
   // View the Transaction History by Account ID 
    
    public List<Transaction> getTransactions(long accountId, int page, int size) throws AccessException {
    	
    	int offset = (page - 1) * size;

        String sql = """
        		   SELECT Transaction_id, Account_id, Type, amount, Date, Amount
        		    FROM Transaction  
        		    WHERE Account_id = ? 
        		       ORDER BY txn_date 
        		       DESC LIMIT ? OFFSET ?""";

        List<Transaction> transactions = new ArrayList<>();

		try (Connection con = DBConnection.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, accountId);
			ps.setInt(2, size);
			ps.setInt(3, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
               Transaction txn = new Transaction();
			
				txn.setacc_id(rs.getInt("acc_id"));
				txn.settype(rs.getString("type"));
				txn.setamount(rs.getBigDecimal("amount"));

				transactions.add(txn);

		    }
		}
		catch (Exception e) {
			throw new AccessException("Failed to fetch transactions", e);
		}

		return transactions;
	}
    
    
    
 // Batch Insertions with Commit & Rollback
    public void saveBatch(List<BigDecimal> amounts, int userId) throws Exception {

        String sql = "INSERT INTO Transactions(cust_id, amount, type) VALUES(?,?,?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();

            // 1. Disable auto-commit
            con.setAutoCommit(false);

            ps = con.prepareStatement(sql);

            for (BigDecimal amt : amounts) {
                ps.setInt(1, userId);
                ps.setBigDecimal(2, amt);
                ps.setString(3, "CREDIT");
                ps.addBatch();
            }

            // 2. Execute batch
            ps.executeBatch();

            // 3. Commit if everything successful
            con.commit();
            System.out.println("Batch inserted successfully!");

        } catch (Exception e) {

            // 4. Rollback if any error occurs
            if (con != null) {
                con.rollback();
                System.out.println("Transaction rolled back!");
            }
            throw e;

        } finally {
            if (ps != null) ps.close();
            if (con != null) {
                con.setAutoCommit(true); // Reset back
                con.close();
            }
        }
    }
    
    
}