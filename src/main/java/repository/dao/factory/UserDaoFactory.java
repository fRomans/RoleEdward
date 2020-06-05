package repository.dao.factory;

import repository.dao.UserDao;
import repository.dao.UserDaoHibernateImpl;
import repository.dao.UserDaoJDBCImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory implements DaoAbstractFactory {

    private final Properties properties = new Properties();
    private static final String DEFAULT_VALUE = "jdbc";

    @Override
    public UserDao createUserDao() {
        if (getProperties().equals("jdbc")) {
            return new UserDaoJDBCImpl();
        }
        if (getProperties().equals("hibernate")) {
            return new UserDaoHibernateImpl();
        } else {
            throw new RuntimeException("Неверное значение в файле конфигурации!");
        }
    }

    private String getProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("dao.properties")) {
            this.properties.load(in);
            return properties.getProperty("dao");
        } catch (IOException e) {
            return DEFAULT_VALUE;
        }
    }
}
