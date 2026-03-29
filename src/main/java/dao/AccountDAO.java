package dao;

import model.Account;
import util.AccountNoGenerator;
import dbconfiguration.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

	public void create(Account account) throws SQLException {

		String sql = "INSERT INTO Accounts(Account_Number, Customer_ID, Account_type, Balance) VALUES(?,?,?,?)";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			String accountNumber = AccountNoGenerator.generateAccountNumber();
			ps.setString(1, accountNumber);
			ps.setInt(2, account.getCustomerId());
			ps.setString(3, account.getAccountType());
			ps.setBigDecimal(4, account.getBalance());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}

	public static List<Account> findByCustomerId(String username) throws SQLException {

		List<Account> list = new ArrayList<>();
		String sql = "SELECT * FROM Accounts A JOIN Customer C ON A.Customer_ID = C.Customer_ID WHERE C.username = ?;";

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
		catch (SQLException e) {
			throw new SQLException(e);
		}
		return list;
	}


	public BigDecimal findBalance(int accountId) throws SQLException {

		String sql = "SELECT * FROM Accounts WHERE Account_ID=?";

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
		catch (SQLException e) {
			throw new SQLException(e);
		}
    }
}
