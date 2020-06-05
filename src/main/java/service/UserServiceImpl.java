package service;

import model.User;
import repository.dao.factory.DaoAbstractFactory;
import repository.dao.factory.UserDaoFactory;
import repository.dao.UserDao;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static volatile UserServiceImpl instance;

    private final UserDao userDAO;

    private UserServiceImpl() {
        DaoAbstractFactory abstractFactory = new UserDaoFactory();
        userDAO = abstractFactory.createUserDao();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void addNewUser(User user) {
        userDAO.create(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.readById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.readByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.readAll();
    }

    @Override
    public void editUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.delete(id);
    }
}
