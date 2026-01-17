package com.example.dynamic_queries.service;

import com.example.dynamic_queries.entity.Activity;
import com.example.dynamic_queries.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ActivityServiceIntegrationTest {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityRepository activityRepository;

    @BeforeEach
    public void setUp() {
        activityRepository.deleteAll();
    }

    @Test
    public void aValidActivityShouldBeCreated() {
        Activity activity = Activity.builder().activityId(12).name("example").build();
        Activity createdActivity = activityService.addActivity(activity);
        assertEquals(activity.getActivityId(), createdActivity.getActivityId());
        assertEquals(activity.getName(), createdActivity.getName());
    }

    @Test
    public void anActivityShouldBeFoundByCode() {
        Activity activity = Activity.builder().activityId(12).code("example").build();
        Activity secondActivity = Activity.builder().activityId(13).code("example 2").build();
        activityService.addActivity(activity);
        activityService.addActivity(secondActivity);

        List<Activity> activities = activityService.findActivity("example", null, null);
        assertEquals(1, activities.size());
        assertEquals(12, activities.get(0).getActivityId());
    }

    @Test
    public void anActivityShouldBeFoundByName() {
        Activity activity = Activity.builder().activityId(12).code("example").name("name 1").build();
        Activity secondActivity = Activity.builder().activityId(13).code("example 2").name("name 2").build();
        activityService.addActivity(activity);
        activityService.addActivity(secondActivity);

        List<Activity> activities = activityService.findActivity(null, "name 2", null);
        assertEquals(1, activities.size());
        assertEquals(13, activities.get(0).getActivityId());
    }

    @Test
    public void anActivityShouldBeFoundByIsClosed() {
        Activity activity = Activity.builder().activityId(12).code("example").isClosed(true).build();
        Activity secondActivity = Activity.builder().activityId(13).code("example 2").isClosed(false).build();
        activityService.addActivity(activity);
        activityService.addActivity(secondActivity);

        List<Activity> activities = activityService.findActivity(null, null, true);
        assertEquals(1, activities.size());
        assertEquals(12, activities.get(0).getActivityId());
    }


    @Test
    public void allActivityShouldBeFoundIfThereAreNoFilters() {
        Activity activity = Activity.builder().activityId(12).code("example").name("name 1").build();
        Activity secondActivity = Activity.builder().activityId(13).code("example 2").name("name 2").build();
        activityService.addActivity(activity);
        activityService.addActivity(secondActivity);

        List<Activity> activities = activityService.findActivity(null, null, null);
        assertEquals(2, activities.size());
    }

    @Test
    public void anActivityShouldBeFoundIfThereAreMultipleFilters() {
        Activity activity = Activity.builder().activityId(12).code("example").name("name").build();
        Activity secondActivity = Activity.builder().activityId(13).code("example").name("name").build();
        activityService.addActivity(activity);
        activityService.addActivity(secondActivity);

        List<Activity> activities = activityService.findActivity("example", "name", null);
        assertEquals(2, activities.size());
    }


}