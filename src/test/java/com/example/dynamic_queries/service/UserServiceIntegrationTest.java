package com.example.dynamic_queries.service;

import com.example.dynamic_queries.entity.User;
import com.example.dynamic_queries.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void aValidUserShouldBeCreated() {
        User user = User.builder().userId(12).username("example").build();
        User createdUser = userService.addUser(user);
        assertEquals(user.getUserId(), createdUser.getUserId());
        assertEquals(user.getUsername(), createdUser.getUsername());
    }

    @Test
    public void aUsersShouldBeFoundByUsername() {
        User user = User.builder().userId(12).username("example").build();
        User secondUser = User.builder().userId(13).username("example 2").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers("example", null, null, null);
        assertEquals(1, users.size());
        assertEquals(12, users.get(0).getUserId());
    }

    @Test
    public void aUsersShouldBeFoundByEmail() {
        User user = User.builder().userId(12).username("example").email("email 1").build();
        User secondUser = User.builder().userId(13).username("example 2").email("email 2").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers(null, "email 2", null, null);
        assertEquals(1, users.size());
        assertEquals(13, users.get(0).getUserId());
    }

    @Test
    public void aUsersShouldBeFoundByFirstName() {
        User user = User.builder().userId(12).username("example").firstName("firstName 1").build();
        User secondUser = User.builder().userId(13).username("example 2").firstName("firstName 2").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers(null, null, "firstName 1", null);
        assertEquals(1, users.size());
        assertEquals(12, users.get(0).getUserId());
    }

    @Test
    public void aUsersShouldBeFoundByLastName() {
        User user = User.builder().userId(12).username("example").lastName("lastName 1").build();
        User secondUser = User.builder().userId(13).username("example 2").lastName("lastName 2").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers(null, null, null, "lastName 1");
        assertEquals(1, users.size());
        assertEquals(12, users.get(0).getUserId());
    }

    @Test
    public void allUsersShouldBeFoundIfThereAreNoFilters() {
        User user = User.builder().userId(12).username("example").lastName("lastName 1").build();
        User secondUser = User.builder().userId(13).username("example 2").lastName("lastName 2").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers(null, null, null, null);
        assertEquals(2, users.size());
    }

    @Test
    public void aUserShouldBeFoundIfThereAreMultipleFilters() {
        User user = User.builder().userId(12).username("example").lastName("lastName").build();
        User secondUser = User.builder().userId(13).username("example").lastName("lastName").build();
        userService.addUser(user);
        userService.addUser(secondUser);

        List<User> users = userService.getUsers("example", null, null, "lastName");
        assertEquals(2, users.size());
    }


}