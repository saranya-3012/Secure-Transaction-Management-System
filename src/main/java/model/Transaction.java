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

    @SuppressWarnings("unused")
    public int getTransactionId() {
        return transactionId;
    }
    @SuppressWarnings("unused")
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @SuppressWarnings("unused")
    public int getAccountId() {
        return accountId;
    }
    @SuppressWarnings("unused")
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @SuppressWarnings("unused")
    public BigDecimal getAmount() {
        return amount;
    }
    @SuppressWarnings("unused")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @SuppressWarnings("unused")
    public String getType() {
        return type;
    }
    @SuppressWarnings("unused")
    public void setType(String type) {
        this.type = type;
    }

    @SuppressWarnings("unused")
    public BigDecimal getTotalAmount() {
        return totalamount;
    }
    @SuppressWarnings("unused")
    public void setTotalAmount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    @SuppressWarnings("unused")
    public String getStatus(){
        return status;
    }
    @SuppressWarnings("unused")
    public void setStatus(String status){
        this.status = status;
    }

    @SuppressWarnings("unused")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @SuppressWarnings("unused")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
