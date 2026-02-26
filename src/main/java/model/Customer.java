package model;

public class Customer {
    private String cust_id;
    private String role;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Constructors
    public Customer() {}

    public Customer(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters & Setters
    public String getcust_id() { 
    	return cust_id; 
    }
    public void setid(String cust_id) { 
    	this.cust_id = cust_id; 
    }

    
    public String getName() { 
    	return name;
    }
    public void setName(String name) { 
    	this.name = name; 
    }

    
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
    	this.email = email;
    }

    
    public String getphone() { 
    	return phone; 
    }
    public void setphone(String phone) { 
    	this.phone = phone; 
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