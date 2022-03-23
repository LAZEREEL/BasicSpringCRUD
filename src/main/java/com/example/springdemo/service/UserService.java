package com.example.springdemo.service;


import com.example.springdemo.dao.UserRepository;
import com.example.springdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    @Autowired
    UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public List<User> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

    public User findById(Integer id) {
        Optional<User> optional = repository.findById(id);

        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void save(User user) {
        repository.save(user);
    }


}
