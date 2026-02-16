package util;

public class Validation {

    // Name Validation
    public static boolean ValidName(String name) {
        if(name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

    // Email Validation
    public static boolean ValidEmail(String email) {
        if(email == null || !email.contains("@")) {
            return false;
        }
        return true;
    }

    // Amount Validation
    public static boolean ValidAmount(double amt) {
        if(amt <= 0) {
            return false;
        }
        return true;
    }

    // Password Validation
    public static boolean ValidPass(String pass) {
        if(pass == null || pass.length() < 8) {
            return false;
        }
        return true;
    }

    // Role Validation
    public static boolean ValidRole(String role) {
        if(role == null || role.isEmpty()) {
            return false;
        }
        return true;
    }
}
