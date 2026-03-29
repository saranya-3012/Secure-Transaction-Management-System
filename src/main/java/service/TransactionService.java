package service;

import dao.TransactionDAO;

public class TransactionService {

	private static final TransactionDAO transactionDAO = new TransactionDAO();

	private TransactionService(){
		throw new UnsupportedOperationException("Utility class");
	}

	public static void transfer(int fromAccount, int toAccount, double amount) throws Exception {
		transactionDAO.transferMoney(fromAccount, toAccount, amount);
	}
}
