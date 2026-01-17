package com.example.dynamic_queries.controller;

import com.example.dynamic_queries.entity.User;
import com.example.dynamic_queries.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public List<User> getUsers(
            @PathVariable(required = false) String username,
            @PathVariable(required = false) String email,
            @PathVariable(required = false) String firstName,
            @PathVariable(required = false) String lastName
    ) {
        return userService.getUsers(username, email, firstName, lastName);
    }
}
