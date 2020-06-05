package repository.config;

import com.mysql.cj.jdbc.Driver;
import model.User;
import org.hibernate.cfg.Configuration;
import repository.executor.Executor;
import util.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static volatile DBHelper instance;

    private static Properties properties;

    public static final String TABLE_NAME = "users";

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = getMysqlConnection();
        createTableIfNotExists(new Executor(connection)); //Если таблицы нет, она будет создана
        return connection;
    }

    public Configuration getConfiguration() {
        return getMySqlConfiguration();
    }

    private static Connection getMysqlConnection() {
        try {
            properties = PropertyReader.getProperty("jdbc.properties");
            DriverManager.registerDriver((Driver) Class.forName(properties.getProperty("jdbc.driver")).newInstance());
            String url = properties.getProperty("jdbc.url") +
                    properties.getProperty("jdbc.database") +
                    "?user=" + properties.getProperty("jdbc.user") +
                    "&password=" + properties.getProperty("jdbc.password") +
                    "&serverTimezone=" + properties.getProperty("jdbc.timezone");
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createTableIfNotExists(Executor executor) {
        try {
            executor.execUpdate("create table if not exists " + TABLE_NAME + " (id bigint auto_increment, " +
                    "firstName varchar(256), lastName varchar(256), age tinyint, email varchar(256), " +
                    "password varchar(256), role varchar(255), " +
                    "primary key (id), unique key idx_users_email (email)) default charset=utf8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        properties = PropertyReader.getProperty("hibernate.properties");
        configuration.setProperties(properties);
        return configuration;
    }
}
