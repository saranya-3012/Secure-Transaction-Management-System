package model;

import java.math.BigDecimal;

public class Transaction {
    private long acc_id;
    private String type;
    private BigDecimal amount;

    // Constructors
    public Transaction() {}

    public Transaction(long acc_id, String type, BigDecimal amount) {
        this.acc_id = acc_id;
        this.type = type;
        this.amount = amount;
    }

    // Getters & Setters
    public long getacc_id() { 
    	return acc_id; 
    }
    public void setacc_id(long acc_id) { 
    	this.acc_id = acc_id; 
    }

    
    public String gettype() { 
    	return type;
    }
    public void settype(String type) { 
    	this.type = type; 
    }

    public BigDecimal getamount() { 
    	return amount; 
    }
    public void setamount(BigDecimal amount) { 
    	this.amount = amount; 
    }
}