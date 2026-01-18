package com.example.dynamic_queries.controller;

import com.example.dynamic_queries.entity.Activity;
import com.example.dynamic_queries.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class ActivityControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    ActivityRepository activityRepository;

    @BeforeEach
    public void setUp() {
        activityRepository.deleteAll();
    }


    @Test
    void aGetRequestWithEmptyTableShouldReturnAnEmptyResponse() {
        restTestClient.get()
                .uri("http://localhost:%d/activity".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[]");
    }

    @Test
    void aGetRequestWithActivitysShouldReturnSomeResults() {
        activityRepository.save(Activity.builder().activityId(1).code("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/activity".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"activityId\":1,\"code\":\"test\",\"name\":null,\"isClosed\":null}]");
    }

    @Test
    void aGetRequestWithActivityIdShouldReturnSomeResults() {
        activityRepository.save(Activity.builder().activityId(1).code("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/activity?activityId=1".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"activityId\":1,\"code\":\"test\",\"name\":null,\"isClosed\":null}]");
    }

    @Test
    void aGetRequestWithNameShouldReturnSomeResults() {
        activityRepository.save(Activity.builder().activityId(1).name("test").build());

        restTestClient.get()
                .uri("http://localhost:%d/activity?name=test".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"activityId\":1,\"code\":null,\"name\":\"test\",\"isClosed\":null}]");
    }

    @Test
    void aGetRequestWithIsClosedShouldReturnSomeResults() {
        activityRepository.save(Activity.builder().activityId(1).isClosed(true).build());

        restTestClient.get()
                .uri("http://localhost:%d/activity?isClosed=true".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"activityId\":1,\"code\":null,\"name\":null,\"isClosed\":true}]");
    }

}
