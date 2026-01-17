package com.example.dynamic_queries.repository;

import com.example.dynamic_queries.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    EntityManager entityManager;

    public List<User> getUsers(String username, String email, String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate predicate = criteriaBuilder.and();

        if(username != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("username"), username));
        }
        if(email != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("email"), email));
        }
        if(firstName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("firstName"), firstName));
        }
        if(lastName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("lastName"), lastName));
        }

        criteriaQuery.where(predicate);
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Transactional
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void deleteAll() {
        entityManager.createNativeQuery("DELETE FROM users").executeUpdate();
    }
}
