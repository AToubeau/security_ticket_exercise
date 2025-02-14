package com.example.spring.demo.security_exo.api.model.login;


import com.example.spring.demo.security_exo.dal.domain.Role;
import com.example.spring.demo.security_exo.dal.domain.User;

import java.util.Set;

public class LoginResponseBody {
    private Long id;
    private String email;
    private String username;
    private Set<Role> roles;
    private String token;

    public LoginResponseBody(Long id, String email, String username, Set<Role> roles, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }

    public LoginResponseBody(Long id, String email, String userName, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.username = userName;
        this.roles = roles;
        this.username = userName;
    }

    public static LoginResponseBody fromEntity(User user) {
        return new LoginResponseBody(user.getId(), user.getEmail(), user.getUsername(), user.getRoles());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
