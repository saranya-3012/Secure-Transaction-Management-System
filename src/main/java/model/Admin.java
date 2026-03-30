package model;

import java.time.LocalDateTime;

public class Admin {

    private int adminId;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
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
    public String getRole() {
        return role;
    }
    @SuppressWarnings("unused")
    public void setRole(String role) {
        this.role = role;
    }

    @SuppressWarnings("unused")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
