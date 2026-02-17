package util;

import java.util.regex.Pattern;

public class Validation {
	
	   // Name Pattern
	   private static final String USERNAME = "^[a-zA-Z0-9_]{3,20}$";
	
    	// Email Pattern
    	private static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	    // Indian phone number (10 digits)
	    // Start with 6-9, Numbers between 0-9, After 1st have 9 digits
	    private static final String PHONE = "^[6-9][0-9]{9}$";

	    // Password rules:
	    // Min 8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special char
	    private static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	    // Amount Pattern
		private static final String AMOUNT = "^\\d+(\\.\\d{1,2})?$";
		
		
		
		
	    // Name validation
		public static boolean isValidUsername(String username) {
			return username != null && Pattern.matches(USERNAME, username);
		}
	    
	    // Email Validation
	    public static boolean isValidEmail(String email) {
	        return email != null && Pattern.matches(EMAIL, email);
	    }
   

	    // Phone Validation
	    public static boolean isValidPhone(String phone) {
	        return phone != null && Pattern.matches(PHONE, phone);
	    }

	    // Password Validation
	    public static boolean isValidPassword(String password) {
	        return password != null && Pattern.matches(PASSWORD, password);
	    }
	    
     	 // Amount Validation
	    public static boolean isValidAmount(String amount) {
	    	return amount != null && Pattern.matches(AMOUNT, amount);
	    }
	}

   
