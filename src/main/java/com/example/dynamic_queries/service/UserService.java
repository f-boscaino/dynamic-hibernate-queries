package com.example.dynamic_queries.service;

import com.example.dynamic_queries.entity.User;
import com.example.dynamic_queries.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(String username, String email, String firstName, String lastName) {
        return userRepository.getUsers(username, email, firstName, lastName);
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }
}
