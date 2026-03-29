package util;

import java.util.regex.Pattern;

public final class Validation {

	private Validation(){
		throw new UnsupportedOperationException("Utility class");
	}

	   private static final String USERNAME = "^[a-z0-9_]{5,15}$";

	   private static final String EMAIL = "^[a-z0-9_.-]+@[a-z.-]+$";

	   private static final String PHONE = "^[6-9]\\d{9}$";

	   private static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

		public static boolean isValidUsername(String username) {
			return username == null || !Pattern.matches(USERNAME, username);
		}

	    public static boolean isValidEmail(String email) {
	        return email != null && Pattern.matches(EMAIL, email);
	    }   

	    public static boolean isValidPhone(String phone) {
	        return phone != null && Pattern.matches(PHONE, phone);
	    }

	    public static boolean isValidPassword(String password) {
	        return password == null || !Pattern.matches(PASSWORD, password);
	    }

	}  
