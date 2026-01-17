package com.example.dynamic_queries.repository;

import com.example.dynamic_queries.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityRepository extends JpaRepository<Activity, Integer>, JpaSpecificationExecutor<Activity> {
}
