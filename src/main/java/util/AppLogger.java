package util;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    public static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("Logfile.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
            LOGGER.info("Logger initialized, writing to Logfile.log");
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
