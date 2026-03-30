package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {

    private int accountId;
    private int customerId;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private LocalDateTime createdAt;

    public Account() {}

    public Account(int accountId, int customerId, String accountNumber,
                   BigDecimal balance, String accountType,
                   LocalDateTime createdAt) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.createdAt = createdAt;
    }

    @SuppressWarnings("unused")
    public int getAccountId() {
        return accountId;
    }
    @SuppressWarnings("unused")
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @SuppressWarnings("unused")
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }
    @SuppressWarnings("unused")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
