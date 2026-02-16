package service;

import dao.*;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();

    public double checkBalance(int accountId) throws Exception {
        return accountDAO.getBalance(accountId);
    }

    public void freezeAccount(int accountId) throws Exception {
        accountDAO.updateStatus(accountId, "FROZEN");
    }

    public void activateAccount(int accountId) throws Exception {
        accountDAO.updateStatus(accountId, "ACTIVE");
    }
}

