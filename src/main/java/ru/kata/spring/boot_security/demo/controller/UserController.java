package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
/*
4. Все CRUD-операции и страницы для них должны быть доступны только
пользователю с ролью admin по url: /admin/.
5. Пользователь с ролью user должен иметь доступ только к своей домашней
странице /user, где выводятся его данные. Доступ к этой странице должен
быть только у пользователей с ролью user и admin.
Не забывайте про несколько ролей у пользователя!
6. Настройте logout с любой страницы с использованием возможностей thymeleaf.
7. Настройте LoginSuccessHandler так, чтобы админа после аутентификации
направляло на страницу /admin, а юзера на его страницу /user.
 */

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        User user = userService.findUser(principal.getName());
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "user";
    }
    @GetMapping("/admin/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "/admin/signup_form";
    }

    @PostMapping("/admin/process_register")
    public String processRegister(User user) {
        userService.registerDefaultUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);

        return "/admin/users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findOne(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "/admin/admin";
    }

    @PostMapping("/admin/save")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
