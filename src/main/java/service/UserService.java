package service;

import model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    Long addUser(User user);

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUserById(Long id);

}
