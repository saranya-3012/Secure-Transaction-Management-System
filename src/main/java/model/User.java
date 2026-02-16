package model;

public class User {
    private String id;
    private String name;
    private String email;
    private int phone;
    private String password;
    private String role;

    // Constructors
    public User() {}

    public User(String name, String email, int phone, String password, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public String getid() { 
    	return id; 
    }
    public void setid(String id) { 
    	this.id = id; 
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

    
    public int getphone() { 
    	return phone; 
    }
    public void setphone(int phone) { 
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
