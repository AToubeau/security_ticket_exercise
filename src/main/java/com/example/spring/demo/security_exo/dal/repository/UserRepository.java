package com.example.spring.demo.security_exo.dal.repository;

import com.example.spring.demo.security_exo.dal.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query( "Select u from User u where u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    @EntityGraph(attributePaths = {"roles", "ticketsCreated", "ticketsAssigned"})
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
