package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("hasButtons", false);
        return "show";
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/admin/user/{id}")
    public String show(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("hasButtons", true);
        return "show";
    }

    @GetMapping("/admin/user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin/user")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PutMapping(value = "/admin/user/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        user.setId(id);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping(value = "/admin/user/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

}