package model;

public class Customer {

    private int customerId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String role;

    public Customer() {}

    @SuppressWarnings("unused")
    public Customer(int customerId, String username, String password, String fullName, String email, String phone, String role) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @SuppressWarnings("unused")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @SuppressWarnings("unused")
    public String getPhone() {
        return phone;
    }
    @SuppressWarnings("unused")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @SuppressWarnings("unused")
    public String getRole() {
        return role;
    }
    @SuppressWarnings("unused")
    public void setRole(String role) {
        this.role = role;
    }

}
