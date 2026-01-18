package com.example.dynamic_queries.controller;

import com.example.dynamic_queries.entity.User;
import com.example.dynamic_queries.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }


    @Test
    void aGetRequestWithEmptyTableShouldReturnAnEmptyResponse() {
        restTestClient.get()
                .uri("http://localhost:%d/user".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[]");
    }

    @Test
    void aGetRequestWithUsersShouldReturnSomeResults() {
        userRepository.addUser(User.builder().userId(1).username("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/user".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"userId\":1,\"username\":\"test\",\"email\":null,\"firstName\":null,\"lastName\":null}]");
    }

    @Test
    void aGetRequestWithUserIdShouldReturnSomeResults() {
        userRepository.addUser(User.builder().userId(1).username("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/user?userId=1".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"userId\":1,\"username\":\"test\",\"email\":null,\"firstName\":null,\"lastName\":null}]");
    }

    @Test
    void aGetRequestWithEmailShouldReturnSomeResults() {
        userRepository.addUser(User.builder().userId(1).email("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/user?email=test".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"userId\":1,\"username\":null,\"email\":\"test\",\"firstName\":null,\"lastName\":null}]");
    }

    @Test
    void aGetRequestWithFirstNameShouldReturnSomeResults() {
        userRepository.addUser(User.builder().userId(1).firstName("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/user?firstName=test".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"userId\":1,\"username\":null,\"email\":null,\"firstName\":\"test\",\"lastName\":null}]");
    }

    @Test
    void aGetRequestWithLastNameShouldReturnSomeResults() {
        userRepository.addUser(User.builder().userId(1).lastName("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/user?lastName=test".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"userId\":1,\"username\":null,\"email\":null,\"firstName\":null,\"lastName\":\"test\"}]");
    }
}
