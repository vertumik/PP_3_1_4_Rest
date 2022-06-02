package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService userService;

    @GetMapping("/user")
    public String pageForUser(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "/user";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("userMain", user);
        return "/admin";
    }

    @GetMapping("/admin/new")
    public String createUserForm(@ModelAttribute("user") User user, Model model) {
        Set<Role> listRoles = userService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        return "/new";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/update-user/{id}")
    public String formUpdateUser(@PathVariable Long id, Model model) {
        Set<Role> listRoles = userService.getAllRoles();
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "/update-user";
    }

    @PostMapping(value = "/admin/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
