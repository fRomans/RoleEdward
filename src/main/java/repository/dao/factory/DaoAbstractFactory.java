package repository.dao.factory;

import repository.dao.UserDao;

public interface DaoAbstractFactory {
    UserDao createUserDao();
}
