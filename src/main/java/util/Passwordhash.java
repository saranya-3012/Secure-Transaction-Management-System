package util;

import java.security.MessageDigest;

public class Passwordhash {

    public static String hashPassword(String password) {
    	
        try {
        	
        	// A class used to normal text into Hash value
            MessageDigest md = MessageDigest.getInstance("SHA-256");    // Secure Hash Algorithm
            
            // Convert the Password into Bytes
            // Hash the Bytes using SHA-256
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } 
        catch (Exception e) {
            throw new RuntimeException("Error hashing password");
        }
    }
}
