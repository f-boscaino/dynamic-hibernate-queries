# Dynamic Queries using JPA Specification with Spring Boot

This example defines a way to handle queries inside a Spring Boot application, using JPA Specification.

There are two ways to handle a dynamic query using JPA Specifications:

- JpaRepository interface with an external Specification class (Activity flow)
- Custom repository without an external Specification class (User flow)

Both need a **Root<>** generic object (the entity class to be queried), a **CriteriaBuilder** object (it lets you
generate the query dynamically using the builder pattern), a list of **Predicate** objects (the query conditions, if no
predicate is defined, the condition will be "WHERE 1=1"), and a **CriteriaQuery** generic object (the query object, that
can be used to apply orderBy, groupBy, and single fields selections).

It is possible to have a Predicate hierarchy (it will translate into WHERE condition groups), concatenating more
predicates using CriteriaBuilder#or() and CriteriaBuilder#and() methods.

For further details, see the Swagger doc: http://localhost:8080/swagger-ui/index.html