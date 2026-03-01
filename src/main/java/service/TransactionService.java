package service;

import dao.TransactionDAO;

public class TransactionService {

	private final TransactionDAO transactionDAO = new TransactionDAO();

	public void transfer(int fromAccount, int toAccount, double amount) throws Exception {
		transactionDAO.transferMoney(fromAccount, toAccount, amount);
	}
}