package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User addNew(@RequestBody User user) {
        List<Role> listroles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listroles.add(roleService.getByName(role.getName()));
        }
        user.setRoles(listroles);
        userService.addUser(user);
        return user;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") int id) {
        List<Role> listroles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listroles.add(roleService.getByName(role.getName()));
        }
        user.setRoles(listroles);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/current_user")
    public User showCurrentUser(Principal principal) {
        return userService.getUserByName(principal.getName());
    }
}
