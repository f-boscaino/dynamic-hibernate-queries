package com.example.dynamic_queries.service;

import com.example.dynamic_queries.entity.Activity;
import com.example.dynamic_queries.repository.ActivityRepository;
import com.example.dynamic_queries.repository.ActivitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> findActivity(String code, String name, Boolean isClosed) {
        Specification<Activity> specification = new ActivitySpecification(code, name, isClosed);
        return activityRepository.findAll(specification);
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }
}
