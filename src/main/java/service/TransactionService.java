package service;

import dao.TransactionDAO;

import java.sql.SQLException;

public class TransactionService {

	private static final TransactionDAO transactionDAO = new TransactionDAO();

	private TransactionService() {}

	public static void transfer(int fromAccount, int toAccount, double amount) throws SQLException {
		try {
			transactionDAO.transferMoney(fromAccount, toAccount, amount);
		}
		catch (SQLException e){
			throw new SQLException(e);
		}
	}
}
