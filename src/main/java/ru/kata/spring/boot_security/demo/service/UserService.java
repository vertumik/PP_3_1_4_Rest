package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveOrUpdate(User user, Set<Role> roles);
    void delete(Long id);
    User findUserById (Long id);
    void addOrUpdateUser(User user);
    List<User> getAllUsers();
    Set<Role> getAllRoles();
}
