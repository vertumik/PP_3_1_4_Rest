package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    void deleteUserById(int id);

    void updateUser(User user);

    void addUser(User user);

    User getUserById(int id);

    User getByUserName(String username);
}
