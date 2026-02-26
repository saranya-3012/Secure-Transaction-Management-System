package model;

import java.math.BigDecimal;

public class Account {
	private long acc_id;
    private int cust_id;
    private String acc_no;
    private BigDecimal balance;
    private String name;

    // Constructors
    public Account() {}

    public Account(long acc_id, int cust_id, String acc_no, BigDecimal balance, String name) {
        this.cust_id = cust_id;
        this.acc_no = acc_no;
        this.balance = balance;
        this.acc_id = acc_id;
        this.name = name;
    }

    // Getters & Setters
    public long getacc_id() { 
    	return acc_id; 
    }
    public void setacc_id(long acc_id) { 
    	this.acc_id = acc_id; 
    }
    
    public int getcust_id() { 
    	return cust_id; 
    }
    public void setcust_id(int cust_id) { 
    	this.cust_id = cust_id; 
    }

    
    public BigDecimal getbalance() { 
    	return balance;
    }
    public void setbalance(BigDecimal balance) { 
    	this.balance = balance; 
    }

    
    public String getacc_no() { 
        return acc_no; 
    }
    public void setacc_no(String acc_no) { 
    	this.acc_no = acc_no;
    }  
    
    public String getName() { 
    	return name;
    }
    public void setName(String name) { 
    	this.name = name; 
    }
}