package dao;

import model.Account;
import util.AccountNoGenerator;
import dbconfiguration.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

	public void create(Account account) throws Exception {

		String sql = "INSERT INTO Accounts(account_number, customer_id, Account_type, balance) VALUES(?,?,?,?)";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			String AccountNumber = AccountNoGenerator.generateAccountNumber();
			ps.setString(1, AccountNumber);
			ps.setInt(2, account.getCustomerId());
			ps.setString(3, account.getAccountType());
			ps.setBigDecimal(4, account.getBalance());

			ps.executeUpdate();
		}
	}

	public static List<Account> findByCustomerId(String username) throws Exception {

		List<Account> list = new ArrayList<>();
		String sql = "SELECT * FROM Accounts A JOIN Customer C ON A.customer_id = C.customer_id WHERE C.username = ?;";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account account = new Account(
						rs.getInt("account_id"),
						rs.getInt("customer_id"),
						rs.getString("account_number"),
						rs.getBigDecimal("balance"),
						rs.getString("account_type"),
						rs.getTimestamp("created_at").toLocalDateTime()
				);
				list.add(account);
			}
		}
		return list;
	}


	public BigDecimal findBalance(int accountId) throws Exception {

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