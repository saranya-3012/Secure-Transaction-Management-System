package model;

public class Account {
    private String accid;
    private String userid;
    private double balance;
    private String status;

    // Constructors
    public Account() {}

    public Account(String accid, String userid, double balance, String status) {
        this.accid = accid;
        this.userid = userid;
        this.balance = balance;
        this.status = status;
    }

    // Getters & Setters
    public String getaccid() { 
    	return accid; 
    }
    public void setaccid(String accid) { 
    	this.accid = accid; 
    }

    public String getuserid() { 
    	return userid;
    }
    public void setuserid(String userid) { 
    	this.userid = userid; 
    }

    public double getbalance() { 
        return balance; 
    }
    public void setbalance(double balance) { 
    	this.balance = balance;
    }

    public String getstatus() { 
        return status; 
    }
    public void setstatus(String status) { 
    	this.status = status;
    }
}
