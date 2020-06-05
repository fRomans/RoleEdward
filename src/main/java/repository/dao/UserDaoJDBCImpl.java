package repository.dao;

import model.User;
import repository.config.DBHelper;
import repository.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Executor executor;

    public UserDaoJDBCImpl() {
        Connection connection = DBHelper.getInstance().getConnection();
        this.executor = new Executor(connection);
    }

    @Override
    public void create(User model) {
        try {
            executor.execUpdate("insert into " + DBHelper.TABLE_NAME
                    + " (firstName, lastName, age, email, password, role) values ('"
                    + model.getFirstName() + "', '" + model.getLastName() + "', "
                    + model.getAge() + ", '" + model.getEmail() + "', "
                    + model.getPassword() + ", '" + model.getRole() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public User readById(Long id) {
        List<User> list;
        try {
            list = executor.execQuery("select * from users where id='" + id + "'", result -> result.isAfterLast() ? null :
                    new User(result.getLong("id"), result.getString("firstName"), result.getString("lastName"),
                            result.getInt("age"), result.getString("email"),
                            result.getString("password"), result.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User readByEmail(String email) {
        List<User> list;
        try {
            list = executor.execQuery("select * from users where email='" + email + "'", result -> result.isAfterLast() ? null :
                    new User(result.getLong("id"), result.getString("firstName"), result.getString("lastName"),
                            result.getInt("age"), result.getString("email"),
                            result.getString("password"), result.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> readAll() {
        try {
            return executor.execQuery("select * from users", result -> result.isAfterLast() ? null :
                    new User(result.getLong("id"), result.getString("firstName"),
                            result.getString("lastName"), result.getInt("age"),
                            result.getString("email"), result.getString("password"),
                            result.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User model) {
        if (readById(model.getId()) != null) {
            try {
                executor.execUpdate("update " + DBHelper.TABLE_NAME
                        + " set firstName='" + model.getFirstName()
                        + "', lastName='" + model.getLastName()
                        + "', age=" + model.getAge()
                        + ", email='" + model.getEmail()
                        + "', password='" + model.getPassword()
                        + "', role='" + model.getRole()
                        + "' where id=" + model.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (readById(id) != null) {
            try {
                executor.execUpdate("delete from " + DBHelper.TABLE_NAME + " where id=" + id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
