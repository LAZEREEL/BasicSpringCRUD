package com.example.springdemo.dao;

import com.example.springdemo.model.User;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback; behövs inte för default är rollback true

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = true) Default inställt som true
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testAddUser(){
        User user = new User();

        user.setFirstName("Sheep");
        user.setLastName("Generic");
        user.setEmail("bawremyou@gmail.com");
        user.setPassword("boom");

        User u = repository.save(user);

        assertThat(u).isNotNull();

    }

    @Test
    public void testGetAllUsers(){
        User user1 = new User();

        user1.setFirstName("Sheep1");
        user1.setLastName("Generic");
        user1.setEmail("bawremyou1@gmail.com");
        user1.setPassword("boom");

        User user2 = new User();

        user2.setFirstName("Sheep2");
        user2.setLastName("Generic");
        user2.setEmail("bawremyou2@gmail.com");
        user2.setPassword("boom");

        repository.save(user1);
        repository.save(user2);

        Iterable<User> users = repository.findAll();
        int listSize = IterableUtils.size(users);

        assertThat(listSize).isEqualTo(2);

    }

    @Test
    public void testUpdate(){
        User user1 = new User();

        user1.setFirstName("Sheep");
        user1.setLastName("Generic");
        user1.setPassword("boom");
        user1.setEmail("bawremyou@gmail.com");

        User savedUser1 = repository.save(user1);

        Integer userId = savedUser1.getId();

        Optional<User> optionalUser2 = repository.findById(userId);
        User user2 = optionalUser2.get();

        user2.setFirstName("Goat");
        user2.setEmail("imagoatmf@gmail.com");

        repository.save(user2);

        User updatedUser = repository.findById(userId).get();

        assertThat(updatedUser.getFirstName()).isEqualTo("Goat");
        assertThat(updatedUser.getEmail()).isEqualTo("imagoatmf@gmail.com");

    }

}