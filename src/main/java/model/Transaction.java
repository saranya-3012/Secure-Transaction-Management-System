package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private int transactionId;
    private int accountId;
    private BigDecimal amount;
    private String type;
    private BigDecimal totalamount;
    private String status;
    private LocalDateTime createdAt;

    public Transaction() {}

    public Transaction(int transactionId, int accountId,
                       BigDecimal amount, String type,
                       BigDecimal totalamount, String status, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.totalamount = totalamount;
        this.status = status;
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

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}