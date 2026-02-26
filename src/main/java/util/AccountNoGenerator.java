package util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class AccountNoGenerator {
	
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final int RANDOM_SUFFIX=100;


    private AccountNoGenerator() {
	
	   throw new UnsupportedOperationException("Utility class");
    }

	public static String generateAccountNumber() {
		
		int randomSuffix = SECURE_RANDOM.nextInt(RANDOM_SUFFIX);
		
		return String.format("%02d", randomSuffix);

	}

}