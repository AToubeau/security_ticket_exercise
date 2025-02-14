package com.example.spring.demo.security_exo.api.model.user;

import com.example.spring.demo.security_exo.dal.domain.Role;
import com.example.spring.demo.security_exo.dal.domain.User;

import java.util.Set;

public class UserDetailResponseBody {
    private Long id;
    private String username;
    private String email;
    private String token;
    private Set<Role> roles;

    public UserDetailResponseBody(
            Long id,
            String username,
            String email,
            Set<Role> roles
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public static UserDetailResponseBody fromEntity(User user) {
        return new UserDetailResponseBody(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
                );
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
