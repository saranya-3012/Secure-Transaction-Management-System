package model;

public class Transaction {
    private String txnid;
    private String sender;
    private String receiver;
    private String amount;
    private String type;
    private String date;

    // Constructors
    public Transaction() {}

    public Transaction(String txnid, String sender, String receiver, String amount, String type, String date) {
        this.txnid = txnid;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    // Getters & Setters
    public String gettxnid() { 
    	return txnid; 
    }
    public void settxnid(String txnid) { 
    	this.txnid = txnid; 
    }

    public String getsender() { 
    	return sender;
    }
    public void setsender(String sender) { 
    	this.sender = sender; 
    }

    public String getreceiver() { 
        return receiver; 
    }
    public void setreceiver(String receiver) { 
    	this.receiver = receiver;
    }

    public String getamount() { 
        return amount; 
    }
    public void setamount(String amount) { 
    	this.amount = amount;
    }
    
    public String gettype() { 
    	return type; 
    }
    public void settype(String type) { 
    	this.type = type; 
    }

    public String getdate() { 
    	return date;
    }
    public void setdate(String date) { 
    	this.date = date; 
    }
}
