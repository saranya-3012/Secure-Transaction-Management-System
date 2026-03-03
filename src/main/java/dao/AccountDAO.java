package dao;

import model.Account;
import util.AccountNoGenerator;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

	// Create a New account
	public void create(Account account) throws Exception {

		String sql = "INSERT INTO Accounts(account_number, customer_id, balance) VALUES(?,?,?)";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			String AccountNumber = AccountNoGenerator.generateAccountNumber();

			ps.setString(1, AccountNumber);
			ps.setInt(2, account.getCustomerId());
			ps.setBigDecimal(3, account.getBalance());

			ps.executeUpdate();
		}
	}

	// View Account Details by Customer ID
	public List<Account> findByCustomerId(int customerId) throws Exception {

		List<Account> list = new ArrayList<>();
		String sql = "SELECT * FROM accounts WHERE customer_id=?";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Account(
						rs.getInt("account_id"),
						rs.getInt("customer_id"),
						rs.getString("account_number"),
						rs.getBigDecimal("balance"),
						rs.getString("account_type"),
						rs.getTimestamp("created_at").toLocalDateTime()
				));
			}
		}
		return list;
	}


	public BigDecimal findBalance(int accountId) throws Exception {

		List<Account> list = new ArrayList<>();
		String sql = "SELECT * FROM Accounts WHERE account_id=?";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getBigDecimal("balance");
			}
			else{
				return null;
			}
		}
    }
}
