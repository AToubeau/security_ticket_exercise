package com.example.spring.demo.security_exo.bll.service;

import com.example.spring.demo.security_exo.api.model.user.CreateUserRequestBody;
import com.example.spring.demo.security_exo.dal.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    User login(String email, String password);
    User CreateUser(CreateUserRequestBody request);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
