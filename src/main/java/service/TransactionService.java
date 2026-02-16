package service;

import util.DBConnection;
import java.sql.*;

public class TransactionService {

    public void transfer(int fromAcc, int toAcc, double amount) throws Exception {

        Connection con = DBConnection.getConnection();
        
        try {
            con.setAutoCommit(false);

            
            // Debit Money
            String debit = "UPDATE accounts SET balance = (balance - ?) WHERE account_id=?";
            
            PreparedStatement ps1 = con.prepareStatement(debit);
            ps1.setDouble(1, amount);
            ps1.setInt(2, fromAcc);
            ps1.executeUpdate();

            
            // Credit Money
            String credit = "UPDATE accounts SET balance = (balance + ?) WHERE account_id=?";
            
            PreparedStatement ps2 = con.prepareStatement(credit);
            ps2.setDouble(1, amount);
            ps2.setInt(2, toAcc);
            ps2.executeUpdate();

            con.commit();
        } 
        catch (Exception e) {
            con.rollback();
            throw e;
        } 
        finally {
            con.close();
        }
    }
}
