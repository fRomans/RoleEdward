package service;

import model.User;

import java.util.List;

public interface UserService {

    void addNewUser(User user);

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void editUser(User user);

    void deleteUser(long id);
}
