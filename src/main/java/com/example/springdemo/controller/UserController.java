package com.example.springdemo.controller;


import com.example.springdemo.model.User;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users")
    public String findAll(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("userList", service.findByKeyword(keyword));
        } else {
            List<User> users = service.findAll();
            model.addAttribute("userList", users);
        }
        return "users";
    }

    @GetMapping("/users/new")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());

        return "user_form";
    }

    @GetMapping("/users/edit{id}")
    public String showEditUser(@PathVariable(value = "id") Integer id, Model model) {

        User user = service.findById(id);

        model.addAttribute("user", user);

        return "user_form";
    }


    @GetMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") User user) {
        service.delete(user);

        return "redirect:/users";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);

        return "redirect:/users";
    }

}
