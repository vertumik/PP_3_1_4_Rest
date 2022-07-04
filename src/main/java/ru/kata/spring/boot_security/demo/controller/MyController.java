package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class MyController {

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    public String userList(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("rolesList", userService.getAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("authorisedUser", (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "admin";
    }
}
