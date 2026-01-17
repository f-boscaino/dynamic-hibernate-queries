package com.example.dynamic_queries.controller;

import com.example.dynamic_queries.entity.Activity;
import com.example.dynamic_queries.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activity")
    public Activity addActivity(@RequestBody Activity activity) {
        return activityService.addActivity(activity);
    }

    @GetMapping("/activity")
    public List<Activity> getActivities(
            @PathVariable(required = false) String code,
            @PathVariable(required = false) String name,
            @PathVariable(required = false) Boolean isClosed
    ) {
        return activityService.findActivity(code, name, isClosed);
    }
}
