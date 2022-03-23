package com.example.springdemo.dao;


import com.example.springdemo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE u.firstName like %:keyword% OR u.lastName like %:keyword%")
    List<User> findByKeyword(@Param("keyword")String keyword);
}
