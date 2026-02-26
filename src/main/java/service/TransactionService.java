package service;

import model.*;
import dao.*;

public class TransactionService {
	
	// Create New Account
	public Account createNewAccount(String accountNumber) throws Exception {
		return TransactionDAO.getAccountByAccountNumber(accountNumber);
	}

}
