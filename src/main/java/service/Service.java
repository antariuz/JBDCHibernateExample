package service;

import java.util.List;

public interface Service<T> {

    List<T> getAllUser();

    Long addUser(T t);

    T getUserById(Long id);

    void updateUser(T t);

    void deleteUserById(Long id);

}
