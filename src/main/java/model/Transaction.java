package model;

public class Transaction {
    private String user_id;
    private String role;
    private String name;
    private String password;

    // Constructors
    public Transaction() {}

    public Transaction(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters & Setters
    public String getuser_id() { 
    	return user_id; 
    }
    public void setid(String user_id) { 
    	this.user_id = user_id; 
    }

    
    public String getName() { 
    	return name;
    }
    public void setName(String name) { 
    	this.name = name; 
    }
    
    
    public String getpassword() { 
        return password; 
    }
    public void setpassword(String password) { 
    	this.password = password;
    }


    public String getrole() { 
    	return role; 
    }
    public void setrole(String role) { 
    	this.role = role; 
    }
}