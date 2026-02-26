package dao;

import java.math.BigDecimal;
import java.sql.*;

import model.Account;
import util.AccountNoGenerator;
import util.DBConnection;

public class AccountDAO {
	
	// Create New Account
	public String createAccount(Account account) throws SQLException {
		
	   String generatedAccountNo = AccountNoGenerator.generateAccountNumber();
	   
       String sql = "INSERT INTO Account(Customer_ID, Account_No, Balance) VALUES(?,?,?)";

       try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {

           ps.setInt(1, account.getcust_id());
           ps.setString(2, generatedAccountNo);
           ps.setBigDecimal(3, account.getbalance());

           ps.executeUpdate();
           
           return "Account Created Successfully!";
       }
       catch (Exception e) {
    	  e.printStackTrace();
       }
       return null;
	}
	
	
	
  // Get Account Details by Account Number
	public Account getAccountByAccNo(String acc_no) throws Exception {

		String sql = "SELECT Account_ID, Account_No, Balance FROM Account WHERE Account_No = ?";

		try (Connection con = DBConnection.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, acc_no);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account account = new Account();
				account.setacc_id(rs.getLong("Account_ID"));
				account.setacc_no(rs.getString("Account_No"));
				account.setbalance(rs.getBigDecimal("Balance"));
				return account;
			}
		} 
		catch (Exception e) {
			throw new Exception("Failed to get account Number", e);
		}

		return null;
	}
	
	
	
	 // Find Balance by Account Number
		public BigDecimal getBalance(String acc_no) throws Exception {

			String sql = "SELECT Balance FROM Account WHERE Account_No = ?";

			try (Connection con = DBConnection.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, acc_no);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {					
					return(rs.getBigDecimal("Balance"));
				}
			} 
			catch (Exception e) {
				throw new Exception("Failed to get Account Number", e);
			}

			return null;
		}
	
		
		
  // Deposit Amount
	public void deposit(String account_no, BigDecimal amount) throws Exception {

		AccountDAO accdao = new AccountDAO();
		BigDecimal pre_amount = accdao.getBalance(account_no);
		BigDecimal total_amount = pre_amount.add(amount);
		
	    String sql = "UPDATE Account SET Balance = ? WHERE Account_No = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setBigDecimal(1, total_amount);
	        ps.setString(2, account_no);

	        ps.executeUpdate();
	    }
	}
	
	
	
 // Withdrawal Amount
		public void withdrwal(String account_no, BigDecimal amount) throws Exception {

			AccountDAO accdao = new AccountDAO();
			BigDecimal pre_amount = accdao.getBalance(account_no);
			BigDecimal total_amount = pre_amount.subtract(amount);
			
		    String sql = "UPDATE Account SET Balance = ? WHERE Account_No = ?";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setBigDecimal(1, total_amount);
		        ps.setString(2, account_no);

		        ps.executeUpdate();
		    }
		}

}
