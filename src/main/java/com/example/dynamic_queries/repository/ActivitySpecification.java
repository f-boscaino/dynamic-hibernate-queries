package com.example.dynamic_queries.repository;

import com.example.dynamic_queries.entity.Activity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class ActivitySpecification implements Specification<Activity> {

    private final String code;
    private final String name;
    private final Boolean isClosed;

    public ActivitySpecification(String code, String name, Boolean isClosed) {
        this.code = code;
        this.name = name;
        this.isClosed = isClosed;
    }


    @Override
    public @Nullable Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.and();
        if(code != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("code"), code));
        }
        if(name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), name));
        }
        if(isClosed != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isClosed"), isClosed));
        }
        return predicate;
    }
}
