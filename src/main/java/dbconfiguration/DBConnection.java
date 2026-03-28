package dbconfiguration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    // HikariDataSource - Create and manage the database connection pool
    private static final HikariDataSource dataSource;

    // Automatically executes when the class loads by JVM
    static {
        try {
            // Store configuration data in key-value pairs
            Properties props = new Properties();

            // DBConnection.class  - Gives the class object
            // getClassLoader() - Find the path of given file
            // getResourceAsStream() - Open and reads the file data as bytes
            // InputStream - Reads the existing data inside the file as a byte
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties");

            // Stores the byte input into prop object
            // load() - Read and converts the byte data into key-value pair data
            props.load(input);

            // Used to configure the connection pool
            HikariConfig config = new HikariConfig();

            // getProperty() - Retrieve the data inside the file using its key
            config.setJdbcUrl(props.getProperty("url"));
            config.setUsername(props.getProperty("username"));
            config.setPassword(props.getProperty("password"));
            config.setDriverClassName(props.getProperty("driver"));

            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("hikari.maximumPoolSize")));
            config.setMinimumIdle(Integer.parseInt(props.getProperty("hikari.minimumIdle")));
            config.setIdleTimeout(Integer.parseInt(props.getProperty("hikari.idleTimeout")));
            config.setConnectionTimeout(Integer.parseInt(props.getProperty("hikari.connectionTimeout")));

            // Gives the stored connection pool data into HikariDataSource
            dataSource = new HikariDataSource(config);

        }
        catch (Exception e) {
            throw new ExceptionInInitializerError("Database Initialization Failed " + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}