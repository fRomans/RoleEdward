package repository.dao;

import model.User;

import java.util.List;

public interface UserDao {
    void create(User model);

    User readById(Long id);

    User readByEmail(String email);

    List<User> readAll();

    void update(User model);

    void delete(Long id);
}