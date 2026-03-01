package dao;

import com.digital.model.Account;
import com.digital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

	// Create a New account
	public void create(Account account) throws Exception {
		String sql = "INSERT INTO Accounts(Customer_id,Account_number,Balance,Account_type) VALUES(?,?,?,?)";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, account.getCustomerId());
			ps.setString(2, account.getAccountNumber());
			ps.setBigDecimal(3, account.getBalance());
			ps.setString(4, account.getAccountType());

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
}
