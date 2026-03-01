package util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class AccountNoGenerator {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	private AccountNoGenerator() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static String generateAccountNumber() {

		String timePart = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		int randomPart = 1000 + SECURE_RANDOM.nextInt(9000);

		return "ACC" + timePart + randomPart;
	}
}