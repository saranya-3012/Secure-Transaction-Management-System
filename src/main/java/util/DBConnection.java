package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();

            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties");

            props.load(input);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));
            config.setDriverClassName(props.getProperty("db.driver"));

            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("hikari.maximumPoolSize")));
            config.setMinimumIdle(Integer.parseInt(props.getProperty("hikari.minimumIdle")));
            config.setIdleTimeout(Integer.parseInt(props.getProperty("hikari.idleTimeout")));
            config.setConnectionTimeout(Integer.parseInt(props.getProperty("hikari.connectionTimeout")));

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
