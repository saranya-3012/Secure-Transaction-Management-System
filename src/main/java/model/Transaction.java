package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private int transactionId;
    private int accountId;
    private BigDecimal amount;
    private String type;   // CREDIT / DEBIT
    private BigDecimal totalamount;
    private LocalDateTime createdAt;

    public Transaction() {}

    public Transaction(int transactionId, int accountId,
                       BigDecimal amount, String type,
                       BigDecimal totalamount, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.totalamount = totalamount;
        this.createdAt = createdAt;
    }

    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalamount;
    }
    public void setTotalAmount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
